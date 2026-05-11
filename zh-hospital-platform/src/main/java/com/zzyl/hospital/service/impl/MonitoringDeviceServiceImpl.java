package com.zzyl.hospital.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.core.utils.JsonUtils;
import com.huaweicloud.sdk.iotda.v5.IoTDAClient;
import com.huaweicloud.sdk.iotda.v5.model.*;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.exception.base.BaseException;
import com.zzyl.common.utils.DateTimeZoneConverter;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.framework.config.properties.HuaWeiIotConfigProperties;
import com.zzyl.hospital.domain.MonitoringDevice;
import com.zzyl.hospital.domain.VitalSignData;
import com.zzyl.hospital.dto.MonitoringDeviceDto;
import com.zzyl.hospital.mapper.MonitoringDeviceMapper;
import com.zzyl.hospital.mapper.VitalSignDataMapper;
import com.zzyl.hospital.service.IMonitoringDeviceService;
import com.zzyl.hospital.vo.MonitoringDeviceDetailVo;
import com.zzyl.hospital.vo.ProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * 设备Service业务层处理
 *
 * @author alexis
 * @date 2026-02-11
 */
@Service
public class MonitoringDeviceServiceImpl extends ServiceImpl<MonitoringDeviceMapper, MonitoringDevice> implements IMonitoringDeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringDeviceServiceImpl.class);

    @Autowired
    private MonitoringDeviceMapper monitoringDeviceMapper;
    @Autowired
    private IoTDAClient ioTDAClient;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private HuaWeiIotConfigProperties huaWeiIotConfigProperties;
    @Autowired
    private VitalSignDataMapper vitalSignDataMapper;

    /**
     * 查询设备列表
     *
     * @param monitoringDevice 设备
     * @return 设备
     */
    @Override
    public List<MonitoringDevice> selectMonitoringDeviceList(MonitoringDevice monitoringDevice) {
        return monitoringDeviceMapper.selectMonitoringDeviceList(monitoringDevice);
    }

    /**
     * 从物联网平台同步产品列表以及设备列表
     */
    @Override
    public void syncProductList() {
        //1.调用物联网平台接口获取产品列表
        ListProductsRequest request = new ListProductsRequest();
        request.setLimit(50);
        try {
            ListProductsResponse response = ioTDAClient.listProducts(request);
            //2.判断HTTP状态码是否为200
            if (response.getHttpStatusCode() != 200) {
                throw new RuntimeException("获取产品列表失败，HTTP状态码：" + response.getHttpStatusCode());
            }
            //3.存入redis
            redisTemplate.opsForValue().set(CacheConstants.PRODUCT_LIST, JsonUtils.toJSON(response.getProducts()));
        } catch (Exception e) {
            List<ProductVo> localProducts = listLocalProducts();
            redisTemplate.opsForValue().set(CacheConstants.PRODUCT_LIST, JSONUtil.toJsonStr(localProducts));
        }
    }

    /**
     * 获取所有产品列表
     */
    @Override
    public List<ProductVo> allProduct() {
        // 从redis中查询数据
        String jsonStr = redisTemplate.opsForValue().get(CacheConstants.PRODUCT_LIST);
        // 如果数据为空，则返回一个空集合
        if (StringUtils.isEmpty(jsonStr)) {
            return listLocalProducts();
        }
        // 解析数据，并返回
        return JSONUtil.toList(jsonStr, ProductVo.class);
    }

    /**
     * 注册设备
     *
     * @param monitoringDeviceDto 设备信息
     */
    @Override
    @Transactional
    public void registerMonitoringDevice(MonitoringDeviceDto monitoringDeviceDto) {
        //1.判断设备名称是否重复
        Long nameCount = lambdaQuery()
                .eq(MonitoringDevice::getMonitoringDeviceName, monitoringDeviceDto.getMonitoringDeviceName())
                .count();

        if (nameCount > 0) {
            throw new RuntimeException("设备名称已存在");
        }

        //2.判断设备标识是否重复
        Long iotIdCount = lambdaQuery()
                .eq(MonitoringDevice::getIotId, monitoringDeviceDto.getIotId())
                .count();

        if (iotIdCount > 0) {
            throw new RuntimeException("设备标识已存在");
        }

        //3.判断同一位置是否绑定了相同的产品
        Long locationProductCount = lambdaQuery()
                .eq(MonitoringDevice::getBindingLocation, monitoringDeviceDto.getBindingLocation())
                .eq(MonitoringDevice::getLocationType, monitoringDeviceDto.getLocationType())
                .eq(MonitoringDevice::getPhysicalLocationType, monitoringDeviceDto.getPhysicalLocationType())
                .eq(MonitoringDevice::getProductKey, monitoringDeviceDto.getProductKey())
                .count();

        if (locationProductCount > 0) {
            throw new RuntimeException("该位置已绑定相同产品");
        }
        // 随机生成密钥
        String secret = UUID.randomUUID().toString().replaceAll("-", "");
        AddDeviceResponse response = null;
        try {
            response = registerMonitoringDeviceToIoTPlatform(monitoringDeviceDto, secret);
        } catch (Exception e) {
            LOGGER.warn("register device on IoT platform failed, save local device instead. nodeId={}", monitoringDeviceDto.getNodeId(), e);
        }

        MonitoringDevice monitoringDevice = BeanUtil.toBean(monitoringDeviceDto, MonitoringDevice.class);
        monitoringDevice.setSecret(secret);
        if (response != null && StringUtils.isNotEmpty(response.getDeviceId())) {
            monitoringDevice.setIotId(response.getDeviceId());
            monitoringDevice.setNodeId(response.getNodeId());
        } else if (StringUtils.isEmpty(monitoringDevice.getIotId())) {
            monitoringDevice.setIotId(StringUtils.isNotEmpty(monitoringDeviceDto.getNodeId())
                    ? monitoringDeviceDto.getNodeId()
                    : "LOCAL-" + UUID.randomUUID().toString().replace("-", ""));
        }
        save(monitoringDevice);
    }

    /**
     * 查询设备详情
     *
     * @param iotId 设备iotId
     * @return 设备详情
     */
    @Override
    public MonitoringDeviceDetailVo queryMonitoringDeviceDetail(String iotId) {
        if (StringUtils.isEmpty(iotId)) {
            throw new BaseException("设备标识不能为空");
        }
        //1.从数据库中查询设备信息
        MonitoringDevice monitoringDevice = lambdaQuery().eq(MonitoringDevice::getIotId, iotId).one();
        if (ObjectUtil.isNull(monitoringDevice)) {
            throw new BaseException("设备不存在");
        }
        MonitoringDeviceDetailVo monitoringDeviceDetailVo = BeanUtil.toBean(monitoringDevice, MonitoringDeviceDetailVo.class);
        //2.从物联网平台查询设备信息
        ShowDeviceRequest request = new ShowDeviceRequest();
        request.setDeviceId(iotId);
        ShowDeviceResponse response;
        try {
            response = ioTDAClient.showDevice(request);
        } catch (Exception e) {
            LOGGER.warn("query IoT device detail failed, use local device detail. iotId={}", iotId, e);
            monitoringDeviceDetailVo.setMonitoringDeviceStatus("LOCAL");
            return monitoringDeviceDetailVo;
        }
        //3.合并
        monitoringDeviceDetailVo.setMonitoringDeviceStatus(response.getStatus());
        String activeTimeStr = response.getActiveTime();
        // 日期转换
        if (StringUtils.isNotBlank(activeTimeStr)) {
            // 解析UTC时间并转换为上海时区
            LocalDateTime utcTime = LocalDateTimeUtil.parse(activeTimeStr, DatePattern.UTC_MS_PATTERN);
            ZonedDateTime shanghaiTime = utcTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
            // 直接设置转换后的LocalDateTime
            monitoringDeviceDetailVo.setActiveTime(shanghaiTime.toLocalDateTime());
        }

        return monitoringDeviceDetailVo;
    }

    /**
     * 查询设备上报数据
     *
     * @param iotId 设备iotId
     * @return 设备上报数据
     */
    @Override
    public AjaxResult queryServiceProperties(String iotId) {
        try {
            ShowDeviceShadowRequest request = new ShowDeviceShadowRequest();
            request.setDeviceId(iotId);
            ShowDeviceShadowResponse response = ioTDAClient.showDeviceShadow(request);
            if (response.getHttpStatusCode() != 200) {
                throw new BaseException("物联网接口 - 查询设备影子，调用失败");
            }
            List<DeviceShadowData> shadow = response.getShadow();
            if (CollUtil.isEmpty(shadow)) {
                return queryLocalServiceProperties(iotId);
            }
            DeviceShadowProperties reported = shadow.get(0).getReported();
            JSONObject jsonObject = JSONUtil.parseObj(reported.getProperties());
            List<Map<String, Object>> list = new ArrayList<>();
            String eventTimeStr = reported.getEventTime();
            LocalDateTime eventTimeLocalDateTime = LocalDateTimeUtil.parse(eventTimeStr, "yyyyMMdd'T'HHmmss'Z'");
            LocalDateTime eventTime = DateTimeZoneConverter.utcToShanghai(eventTimeLocalDateTime);

            jsonObject.forEach((k, v) -> {
                Map<String, Object> map = new HashMap<>();
                map.put("functionId", k);
                map.put("value", v);
                map.put("eventTime", eventTime);
                list.add(map);
            });
            return AjaxResult.success(list);
        } catch (Exception e) {
            LOGGER.warn("query IoT device shadow failed, use local vital sign data. iotId={}", iotId, e);
            return queryLocalServiceProperties(iotId);
        }
    }

    /**
     * 修改设备
     *
     * @param monitoringDeviceDto 设备信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateMonitoringDevice(MonitoringDeviceDto monitoringDeviceDto) {
        // 1.参数校验
        if (ObjectUtil.isNull(monitoringDeviceDto)) {
            throw new BaseException("设备参数不完整");
        }

        // 4.设备名称唯一性校验（排除自身）
        Long nameCount = lambdaQuery()
                .eq(MonitoringDevice::getMonitoringDeviceName, monitoringDeviceDto.getMonitoringDeviceName())
                .ne(MonitoringDevice::getId, monitoringDeviceDto.getId())
                .count();
        if (nameCount > 0) {
            throw new BaseException("设备名称已存在");
        }

        // 5.同位置同产品唯一性校验（排除自身）
        Long locationProductCount = lambdaQuery()
                .eq(monitoringDeviceDto.getBindingLocation() != null, MonitoringDevice::getBindingLocation, monitoringDeviceDto.getBindingLocation())
                .eq(monitoringDeviceDto.getLocationType() != null, MonitoringDevice::getLocationType, monitoringDeviceDto.getLocationType())
                .eq(monitoringDeviceDto.getPhysicalLocationType() != null, MonitoringDevice::getPhysicalLocationType, monitoringDeviceDto.getPhysicalLocationType())
                .ne(MonitoringDevice::getId, monitoringDeviceDto.getId())
                .count();
        if (locationProductCount > 0) {
            throw new BaseException("该位置已绑定相同产品");
        }

        MonitoringDevice monitoringDevice = BeanUtil.toBean(monitoringDeviceDto, MonitoringDevice.class);
        try {
            UpdateDeviceRequest request = new UpdateDeviceRequest();
            request.setDeviceId(monitoringDeviceDto.getIotId());
            UpdateDevice body = new UpdateDevice();
            body.setDeviceName(monitoringDeviceDto.getMonitoringDeviceName());
            request.setBody(body);
            UpdateDeviceResponse response = ioTDAClient.updateDevice(request);
            if (response.getHttpStatusCode() == 200) {
                monitoringDevice.setIotId(response.getDeviceId());
                monitoringDevice.setNodeId(response.getNodeId());
                if (response.getAuthInfo() != null) {
                    monitoringDevice.setSecret(response.getAuthInfo().getSecret());
                }
            }
        } catch (Exception e) {
            LOGGER.warn("update IoT device failed, update local device only. iotId={}", monitoringDeviceDto.getIotId(), e);
        }
        return updateById(monitoringDevice) ? 1 : 0;
    }

    /**
     * 删除设备
     *
     * @param iotId 设备iotId
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteMonitoringDeviceByIotId(String iotId) {
        // 1.参数校验
        if (StringUtils.isEmpty(iotId)) {
            throw new BaseException("设备标识不能为空");
        }

        // 2.先删除云端设备，404按幂等成功处理
        DeleteDeviceRequest request = new DeleteDeviceRequest();
        request.setDeviceId(iotId);
        try {
            DeleteDeviceResponse response = ioTDAClient.deleteDevice(request);
            Integer httpStatusCode = response.getHttpStatusCode();
            if (ObjectUtil.isNull(httpStatusCode) || (httpStatusCode != 200 && httpStatusCode != 204)) {
                throw new BaseException("物联网接口 - 删除设备，调用失败");
            }
        } catch (ServiceResponseException e) {
            if (e.getHttpStatusCode() != 404) {
                LOGGER.warn("delete IoT device failed, delete local device only. iotId={}", iotId, e);
            }
            LOGGER.warn("物联网平台设备不存在，继续执行本地删除，iotId={}", iotId);
        } catch (Exception e) {
            LOGGER.warn("delete IoT device failed, delete local device only. iotId={}", iotId, e);
        }

        // 3.删除本地设备（本地不存在也按成功处理）
        monitoringDeviceMapper.delete(new LambdaQueryWrapper<MonitoringDevice>().eq(MonitoringDevice::getIotId, iotId));
        return 1;
    }

    /**
     * 查询产品详情
     *
     * @param productKey 产品id
     * @return 产品服务能力详情
     */
    @Override
    public AjaxResult queryProduct(String productKey) {
        // 1.参数校验
        if (StringUtils.isEmpty(productKey)) {
            throw new BaseException("产品标识不能为空");
        }

        // 2.调用物联网平台查询产品详情
        ShowProductRequest request = new ShowProductRequest();
        request.setProductId(productKey);
        ShowProductResponse response;
        try {
            response = ioTDAClient.showProduct(request);
        } catch (Exception e) {
            LOGGER.warn("query IoT product failed, use local product capabilities. productKey={}", productKey, e);
            return queryLocalProductCapabilities(productKey);
        }

        Integer httpStatusCode = response.getHttpStatusCode();
        if (ObjectUtil.isNull(httpStatusCode) || httpStatusCode != 200) {
            return queryLocalProductCapabilities(productKey);
        }

        // 3.返回服务能力列表，空列表时返回空数组
        List<ServiceCapability> serviceCapabilities = response.getServiceCapabilities();
        if (CollUtil.isEmpty(serviceCapabilities)) {
            return AjaxResult.success(Collections.emptyList());
        }
        return AjaxResult.success(serviceCapabilities);
    }

    private List<ProductVo> listLocalProducts() {
        List<MonitoringDevice> devices = list();
        if (CollUtil.isEmpty(devices)) {
            return Collections.emptyList();
        }
        Map<String, ProductVo> productMap = new LinkedHashMap<>();
        for (MonitoringDevice device : devices) {
            if (StringUtils.isEmpty(device.getProductKey())) {
                continue;
            }
            productMap.computeIfAbsent(device.getProductKey(), productKey -> {
                ProductVo productVo = new ProductVo();
                productVo.setProductId(productKey);
                productVo.setName(StringUtils.isNotEmpty(device.getProductName()) ? device.getProductName() : productKey);
                return productVo;
            });
        }
        return new ArrayList<>(productMap.values());
    }

    private AjaxResult queryLocalServiceProperties(String iotId) {
        List<VitalSignData> dataList = vitalSignDataMapper.selectList(new LambdaQueryWrapper<VitalSignData>()
                .eq(VitalSignData::getIotId, iotId)
                .orderByDesc(VitalSignData::getAlarmTime)
                .last("limit 20"));
        if (CollUtil.isEmpty(dataList)) {
            return AjaxResult.success(Collections.emptyList());
        }

        Map<String, Map<String, Object>> latestMap = new LinkedHashMap<>();
        for (VitalSignData data : dataList) {
            latestMap.computeIfAbsent(data.getFunctionId(), functionId -> {
                Map<String, Object> map = new HashMap<>();
                map.put("functionId", functionId);
                map.put("value", data.getDataValue());
                map.put("eventTime", data.getAlarmTime());
                return map;
            });
        }
        return AjaxResult.success(new ArrayList<>(latestMap.values()));
    }

    private AjaxResult queryLocalProductCapabilities(String productKey) {
        List<VitalSignData> dataList = vitalSignDataMapper.selectList(new LambdaQueryWrapper<VitalSignData>()
                .eq(VitalSignData::getProductKey, productKey)
                .select(VitalSignData::getFunctionId));
        Set<String> functionIds = new LinkedHashSet<>();
        if (CollUtil.isNotEmpty(dataList)) {
            for (VitalSignData data : dataList) {
                if (StringUtils.isNotEmpty(data.getFunctionId())) {
                    functionIds.add(data.getFunctionId());
                }
            }
        }
        if (functionIds.isEmpty()) {
            functionIds.add("HeartRate");
            functionIds.add("BloodPressureHigh");
            functionIds.add("BloodPressureLow");
            functionIds.add("Temperature");
            functionIds.add("OxygenSaturation");
        }

        List<Map<String, Object>> properties = new ArrayList<>();
        for (String functionId : functionIds) {
            Map<String, Object> property = new HashMap<>();
            property.put("property_name", functionId);
            property.put("property_id", functionId);
            properties.add(property);
        }

        Map<String, Object> service = new HashMap<>();
        service.put("service_id", "local");
        service.put("service_name", "本地数据模块");
        service.put("properties", properties);
        return AjaxResult.success(Collections.singletonList(service));
    }

    /**
     * 注册设备到物联网平台
     */
    private AddDeviceResponse registerMonitoringDeviceToIoTPlatform(MonitoringDeviceDto monitoringDeviceDto, String secret) {
        // 注册设备--->IoT平台
        AddDeviceRequest request = new AddDeviceRequest();
        AddDevice body = new AddDevice();
        body.withProductId(monitoringDeviceDto.getProductKey());
        body.withDeviceName(monitoringDeviceDto.getMonitoringDeviceName());
        body.withNodeId(monitoringDeviceDto.getNodeId());

        // 秘钥设置
        AuthInfo authInfo = new AuthInfo();
        authInfo.withSecret(secret);
        body.setAuthInfo(authInfo);
        request.withBody(body);
        AddDeviceResponse response;
        try {
            response = ioTDAClient.addDevice(request);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("物联网接口 - 注册设备，调用失败");
        }
    }
}

