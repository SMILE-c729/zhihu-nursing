package com.zzyl.nursing.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.utils.DateTimeZoneConverter;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.nursing.domain.Device;
import com.zzyl.nursing.dto.DeviceDataPageReqDto;
import com.zzyl.nursing.mapper.DeviceMapper;
import com.zzyl.nursing.vo.DevicePropertyReportVo;
import com.zzyl.nursing.vo.NotifyData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.DeviceDataMapper;
import com.zzyl.nursing.domain.DeviceData;
import com.zzyl.nursing.service.IDeviceDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备数据服务层实现
 * @author alexis
 * @date 2026-02-22
 */
@Service
@Slf4j
public class DeviceDataServiceImpl extends ServiceImpl<DeviceDataMapper, DeviceData> implements IDeviceDataService {
    @Autowired
    private DeviceDataMapper deviceDataMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 批量插入设备数据
     *
     * @param iotMsgNotifyData 设备数据
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertDeviceData(NotifyData iotMsgNotifyData) {
        //查询设备
        Device device = deviceMapper.selectDeviceByDeviceId(iotMsgNotifyData.getHeader().getDeviceId());
        //设备不存在
        if (ObjectUtil.isEmpty(device) || ObjectUtil.isEmpty(iotMsgNotifyData)) {
            log.info("设备不存在");
            return;
        }
        // 批量保存设备数据
        iotMsgNotifyData.getBody().getServices().forEach(s -> {
            // 判断属性是否为空
            Map<String, Object> properties = s.getProperties();
            if (CollUtil.isEmpty(properties)) {
                return;
            }

            // 上报时间处理
            String eventTimeStr = s.getEventTime();
            LocalDateTime localDateTime = LocalDateTimeUtil.parse(eventTimeStr, "yyyyMMdd'T'HHmmss'Z'");
            LocalDateTime eventTime = DateTimeZoneConverter.utcToShanghai(localDateTime);

            List<DeviceData> list = new ArrayList<>();

            // key:属性id，value:属性值
            properties.forEach((k, v) -> {
                DeviceData deviceData = BeanUtil.toBean(device, DeviceData.class);
                deviceData.setId(null);  // 清空ID，让数据库自动生成
                deviceData.setAccessLocation(device.getBindingLocation());
                deviceData.setAlarmTime(eventTime);
                deviceData.setFunctionId(k);
                deviceData.setDataValue(v + "");
                list.add(deviceData);
            });
            
            // 批量保存设备数据（如果list为空则跳过）
            if (CollUtil.isNotEmpty(list)) {
                try {
                   saveBatch(list);
                } catch (Exception e) {
                  log.error(e.getMessage(), e);
                    throw e;
                }
            }

        });


    }

    /**
     * 查询设备数据列表
     *
     * @param deviceDataPageReqDto
     */
    @Override
    public TableDataInfo selectDeviceDataList(DeviceDataPageReqDto deviceDataPageReqDto) {
        return null;
    }
}







