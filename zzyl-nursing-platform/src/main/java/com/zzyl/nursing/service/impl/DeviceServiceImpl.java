package com.zzyl.nursing.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        //4.调用物联网平台接口获取设备列表
        syncDeviceList();
    }

    /**
     * 从物联网平台同步设备列表
     */
    private void syncDeviceList() {
        ListDevicesRequest request = new ListDevicesRequest();
        ListDevicesResponse response = ioTDAClient.listDevices(request);
        if (response.getHttpStatusCode() != 200) {
            throw new RuntimeException("获取设备列表失败，HTTP状态码：" + response.getHttpStatusCode());
        }
        //3.存入数据库
        List<QueryDeviceSimplify> devices = response.getDevices();
        convertToDeviceList(devices);
    }

    /**
     * 将物联网平台设备列表转换为本地设备实体列表
     */
    private void convertToDeviceList(List<QueryDeviceSimplify> iotDevices) {
        if (iotDevices.isEmpty()) {
            return;
        }

        List<Device> devicesToSave = new ArrayList<>();

        for (QueryDeviceSimplify iotDevice : iotDevices) {
            // 判断iotDevice.getDeviceId()是否在device数据库中存在
            Device existingDevice = lambdaQuery()
                    .eq(Device::getIotId, iotDevice.getDeviceId())
                    .one();

            if (ObjectUtil.isNull(existingDevice)) {
                // 如果不存在，创建新设备并添加到保存列表
                Device device = new Device();
                device.setIotId(iotDevice.getDeviceId());
                device.setDeviceName(iotDevice.getDeviceName());
                device.setProductKey(iotDevice.getProductId());
                device.setProductName(iotDevice.getProductName());
                device.setNodeId(iotDevice.getNodeId());
                // 随机生成密钥
                String secret = UUID.randomUUID().toString().replaceAll("-", "");
                device.setSecret(secret);
                devicesToSave.add(device);
            }
            // 如果存在则跳过，不进行任何操作
        }
        // 批量保存新增的设备
        if (!devicesToSave.isEmpty()) {
            saveBatch(devicesToSave);
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
        if(response.getHttpStatusCode() != 200) {
            throw new BaseException("物联网接口 - 查询设备影子，调用失败");
        }
        List<DeviceShadowData> shadow = response.getShadow();
        if(CollUtil.isEmpty(shadow)) {
            List<Object> emptyList = Collections.emptyList();
            return AjaxResult.success(emptyList);
        }
        // 获取上报数据的reported （参考返回的json数据）
        DeviceShadowProperties reported = shadow.get(0).getReported();
        // 把数据转换为JSONObject(map)，方便处理
        JSONObject jsonObject = JSONUtil.parseObj(reported.getProperties());
        // 遍历数据，封装到list中
        List<Map<String,Object>>  list = new ArrayList<>();
        // 事件上报时间
        String eventTimeStr = reported.getEventTime();
        // 把字符串转换为LocalDateTime
        LocalDateTime eventTimeLocalDateTime = LocalDateTimeUtil.parse(eventTimeStr, "yyyyMMdd'T'HHmmss'Z'");
        // 时区转换
        LocalDateTime eventTime = DateTimeZoneConverter.utcToShanghai(eventTimeLocalDateTime);

        // k:属性标识，v:属性值
        jsonObject.forEach((k,v) -> {
            Map<String,Object> map = new HashMap<>();
            map.put("functionId", k);
            map.put("value", v);
            map.put("eventTime", eventTime);
            list.add(map);
        });

        // 数据返回
        return AjaxResult.success(list);
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

