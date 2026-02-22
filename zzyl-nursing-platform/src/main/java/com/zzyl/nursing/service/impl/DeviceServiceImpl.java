package com.zzyl.nursing.service.impl;

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
import com.zzyl.nursing.domain.Device;
import com.zzyl.nursing.dto.DeviceDto;
import com.zzyl.nursing.mapper.DeviceMapper;
import com.zzyl.nursing.service.IDeviceService;
import com.zzyl.nursing.vo.DeviceDetailVo;
import com.zzyl.nursing.vo.ProductVo;
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
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private IoTDAClient ioTDAClient;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private HuaWeiIotConfigProperties huaWeiIotConfigProperties;

    /**
     * 查询设备列表
     *
     * @param device 设备
     * @return 设备
     */
    @Override
    public List<Device> selectDeviceList(Device device) {
        return deviceMapper.selectDeviceList(device);
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
            throw new RuntimeException(e);
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
            return Collections.emptyList();
        }
        // 解析数据，并返回
        return JSONUtil.toList(jsonStr, ProductVo.class);
    }

    /**
     * 注册设备
     *
     * @param deviceDto 设备信息
     */
    @Override
    @Transactional
    public void registerDevice(DeviceDto deviceDto) {
        //1.判断设备名称是否重复
        Long nameCount = lambdaQuery()
                .eq(Device::getDeviceName, deviceDto.getDeviceName())
                .count();

        if (nameCount > 0) {
            throw new RuntimeException("设备名称已存在");
        }

        //2.判断设备标识是否重复
        Long iotIdCount = lambdaQuery()
                .eq(Device::getIotId, deviceDto.getIotId())
                .count();

        if (iotIdCount > 0) {
            throw new RuntimeException("设备标识已存在");
        }

        //3.判断同一位置是否绑定了相同的产品
        Long locationProductCount = lambdaQuery()
                .eq(Device::getBindingLocation, deviceDto.getBindingLocation())
                .eq(Device::getLocationType, deviceDto.getLocationType())
                .eq(Device::getPhysicalLocationType, deviceDto.getPhysicalLocationType())
                .eq(Device::getProductKey, deviceDto.getProductKey())
                .count();

        if (locationProductCount > 0) {
            throw new RuntimeException("该位置已绑定相同产品");
        }
        // 随机生成密钥
        String secret = UUID.randomUUID().toString().replaceAll("-", "");
        //4.注册设备到物联网平台
        AddDeviceResponse response = registerDeviceToIoTPlatform(deviceDto, secret);

        //5.补全设备信息并保存到数据库
        Device device = BeanUtil.toBean(deviceDto, Device.class);
        device.setSecret(secret);
        device.setIotId(response.getDeviceId());
        save(device);
    }

    /**
     * 查询设备详情
     *
     * @param iotId 设备iotId
     * @return 设备详情
     */
    @Override
    public DeviceDetailVo queryDeviceDetail(String iotId) {
        if (StringUtils.isEmpty(iotId)) {
            throw new BaseException("设备标识不能为空");
        }
        //1.从数据库中查询设备信息
        Device device = lambdaQuery().eq(Device::getIotId, iotId).one();
        //2.从物联网平台查询设备信息
        ShowDeviceRequest request = new ShowDeviceRequest();
        request.setDeviceId(iotId);
        ShowDeviceResponse response;
        try {
            response = ioTDAClient.showDevice(request);
        } catch (Exception e) {
            throw new BaseException("物联网接口 - 查询设备信息，调用失败");
        }
        //3.合并
        DeviceDetailVo deviceDetailVo = BeanUtil.toBean(device, DeviceDetailVo.class);
        deviceDetailVo.setDeviceStatus(response.getStatus());
        String activeTimeStr = response.getActiveTime();
        // 日期转换
        if (StringUtils.isNotBlank(activeTimeStr)) {
            // 解析UTC时间并转换为上海时区
            LocalDateTime utcTime = LocalDateTimeUtil.parse(activeTimeStr, DatePattern.UTC_MS_PATTERN);
            ZonedDateTime shanghaiTime = utcTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
            // 直接设置转换后的LocalDateTime
            deviceDetailVo.setActiveTime(shanghaiTime.toLocalDateTime());
        }

        return deviceDetailVo;
    }

    /**
     * 查询设备上报数据
     *
     * @param iotId 设备iotId
     * @return 设备上报数据
     */
    @Override
    public AjaxResult queryServiceProperties(String iotId) {

        ShowDeviceShadowRequest request = new ShowDeviceShadowRequest();
        request.setDeviceId(iotId);
        ShowDeviceShadowResponse response = ioTDAClient.showDeviceShadow(request);
        if (response.getHttpStatusCode() != 200) {
            throw new BaseException("物联网接口 - 查询设备影子，调用失败");
        }
        List<DeviceShadowData> shadow = response.getShadow();
        if (CollUtil.isEmpty(shadow)) {
            List<Object> emptyList = Collections.emptyList();
            return AjaxResult.success(emptyList);
        }
        // 获取上报数据的reported （参考返回的json数据）
        DeviceShadowProperties reported = shadow.get(0).getReported();
        // 把数据转换为JSONObject(map)，方便处理
        JSONObject jsonObject = JSONUtil.parseObj(reported.getProperties());
        // 遍历数据，封装到list中
        List<Map<String, Object>> list = new ArrayList<>();
        // 事件上报时间
        String eventTimeStr = reported.getEventTime();
        // 把字符串转换为LocalDateTime
        LocalDateTime eventTimeLocalDateTime = LocalDateTimeUtil.parse(eventTimeStr, "yyyyMMdd'T'HHmmss'Z'");
        // 时区转换
        LocalDateTime eventTime = DateTimeZoneConverter.utcToShanghai(eventTimeLocalDateTime);

        // k:属性标识，v:属性值
        jsonObject.forEach((k, v) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("functionId", k);
            map.put("value", v);
            map.put("eventTime", eventTime);
            list.add(map);
        });

        // 数据返回
        return AjaxResult.success(list);
    }

    /**
     * 修改设备
     *
     * @param deviceDto 设备信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDevice(DeviceDto deviceDto) {
        // 1.参数校验
        if (ObjectUtil.isNull(deviceDto)) {
            throw new BaseException("设备参数不完整");
        }

        // 4.设备名称唯一性校验（排除自身）
        Long nameCount = lambdaQuery()
                .eq(Device::getDeviceName, deviceDto.getDeviceName())
                .ne(Device::getId, deviceDto.getId())
                .count();
        if (nameCount > 0) {
            throw new BaseException("设备名称已存在");
        }

        // 5.同位置同产品唯一性校验（排除自身）
        Long locationProductCount = lambdaQuery()
                .eq(deviceDto.getBindingLocation() != null, Device::getBindingLocation, deviceDto.getBindingLocation())
                .eq(deviceDto.getLocationType() != null, Device::getLocationType, deviceDto.getLocationType())
                .eq(deviceDto.getPhysicalLocationType() != null, Device::getPhysicalLocationType, deviceDto.getPhysicalLocationType())
                .ne(Device::getId, deviceDto.getId())
                .count();
        if (locationProductCount > 0) {
            throw new BaseException("该位置已绑定相同产品");
        }

        // 6.同步到物联网平台（同步可变字段）
        UpdateDeviceRequest request = new UpdateDeviceRequest();
        request.setDeviceId(deviceDto.getIotId());
        UpdateDevice body = new UpdateDevice();
        body.setDeviceName(deviceDto.getDeviceName());
        request.setBody(body);
        // 5.调用物联网平台修改设备
        UpdateDeviceResponse response = ioTDAClient.updateDevice(request);
        if (response.getHttpStatusCode() != 200) {
            throw new BaseException("物联网接口 - 修改设备，调用失败");
        }

        // 7.更新本地设备（保留iotId和secret）
        Device device = BeanUtil.toBean(deviceDto, Device.class);
        device.setIotId(response.getDeviceId());
        device.setNodeId(response.getNodeId());
        device.setSecret(response.getAuthInfo().getSecret());
        return updateById(device) ? 1 : 0;
    }

    /**
     * 删除设备
     *
     * @param iotId 设备iotId
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteDeviceByIotId(String iotId) {
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
                throw new BaseException("物联网接口 - 删除设备，调用失败");
            }
            log.warn("物联网平台设备不存在，继续执行本地删除，iotId={}" + iotId);
        } catch (Exception e) {
            throw new BaseException("物联网接口 - 删除设备，调用失败");
        }

        // 3.删除本地设备（本地不存在也按成功处理）
        deviceMapper.delete(new LambdaQueryWrapper<Device>().eq(Device::getIotId, iotId));
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
            throw new BaseException("物联网接口 - 查询产品详情，调用失败");
        }

        Integer httpStatusCode = response.getHttpStatusCode();
        if (ObjectUtil.isNull(httpStatusCode) || httpStatusCode != 200) {
            throw new BaseException("物联网接口 - 查询产品详情，调用失败");
        }

        // 3.返回服务能力列表，空列表时返回空数组
        List<ServiceCapability> serviceCapabilities = response.getServiceCapabilities();
        if (CollUtil.isEmpty(serviceCapabilities)) {
            return AjaxResult.success(Collections.emptyList());
        }
        return AjaxResult.success(serviceCapabilities);
    }

    /**
     * 注册设备到物联网平台
     */
    private AddDeviceResponse registerDeviceToIoTPlatform(DeviceDto deviceDto, String secret) {
        // 注册设备--->IoT平台
        AddDeviceRequest request = new AddDeviceRequest();
        AddDevice body = new AddDevice();
        body.withProductId(deviceDto.getProductKey());
        body.withDeviceName(deviceDto.getDeviceName());
        body.withNodeId(deviceDto.getNodeId());

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

