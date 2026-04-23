package com.zzyl.hospital.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.hospital.domain.MonitoringDevice;
import com.zzyl.hospital.domain.VitalSignData;
import com.zzyl.hospital.domain.WardRoom;
import com.zzyl.hospital.mapper.VitalSignDataMapper;
import com.zzyl.hospital.mapper.MonitoringDeviceMapper;
import com.zzyl.hospital.mapper.WardRoomMapper;
import com.zzyl.hospital.service.IWardRoomService;
import com.zzyl.hospital.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 房间Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@Service
public class WardRoomServiceImpl extends ServiceImpl<WardRoomMapper, WardRoom> implements IWardRoomService {
    @Autowired
    private WardRoomMapper wardRoomMapper;
    @Autowired
    private MonitoringDeviceMapper monitoringDeviceMapper;
    @Autowired
    private VitalSignDataMapper vitalSignDataMapper;
    @Autowired
    private RedisTemplate<String ,String> redisTemplate;
    /**
     * 查询房间
     *
     * @param id 房间主键
     * @return 房间
     */
    @Override
    public WardRoom selectWardRoomById(Long id) {
        return getById(id);
    }

    /**
     * 查询房间列表
     *
     * @param wardRoom 房间
     * @return 房间
     */
    @Override
    public List<WardRoom> selectWardRoomList(WardRoom wardRoom) {
        return wardRoomMapper.selectWardRoomList(wardRoom);
    }

    /**
     * 新增房间
     *
     * @param wardRoom 房间
     * @return 结果
     */
    @Override
    public int insertWardRoom(WardRoom wardRoom) {
        return save(wardRoom) ? 1 : 0;
    }

    /**
     * 修改房间
     *
     * @param wardRoom 房间
     * @return 结果
     */
    @Override
    public int updateWardRoom(WardRoom wardRoom) {
        return updateById(wardRoom) ? 1 : 0;
    }

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteWardRoomByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 根据楼层 id 获取房间视图对象列表
     *
     * @param wardFloorId 楼层ID
     * @return 房间列表
     */
    @Override
    public List<WardRoomVo> getWardRoomsByWardFloorId(Long wardFloorId) {
        return wardRoomMapper.selectByWardFloorId(wardFloorId);
    }

    /**
     * 获取所有房间（责任患者）
     *
     * @param wardFloorId 楼层ID
     * @return 房间列表
     */

    @Override
    public List<WardRoomVo> getWardRoomsWithNurByWardFloorId(Long wardFloorId) {
      /*  // 1. 查询楼层下房间和病床基础信息
        List<WardRoomVo> wardRoomVos = wardRoomMapper.selectByWardFloorId(wardFloorId);
        if (wardRoomVos == null || wardRoomVos.isEmpty()) {
            return wardRoomVos;
        }

        // 2. 收集房间和病床ID，用于按绑定位置查询设备
        Set<String> wardRoomLocationIds = new HashSet<>();
        Set<String> wardBedLocationIds = new HashSet<>();
        for (WardRoomVo wardRoomVo : wardRoomVos) {
            if (wardRoomVo.getId() != null) {
                wardRoomLocationIds.add(String.valueOf(wardRoomVo.getId()));
            }
            if (wardRoomVo.getWardBedVoList() == null || wardRoomVo.getWardBedVoList().isEmpty()) {
                continue;
            }
            for (WardBedVo wardBedVo : wardRoomVo.getWardBedVoList()) {
                if (wardBedVo != null && wardBedVo.getId() != null) {
                    wardBedLocationIds.add(String.valueOf(wardBedVo.getId()));
                }
            }
        }

        // 3. 分别查询房间设备和病床设备
        List<MonitoringDevice> wardRoomMonitoringDevices = selectFixedMonitoringDevicesByLocation(1, wardRoomLocationIds);
        List<MonitoringDevice> wardBedMonitoringDevices = selectFixedMonitoringDevicesByLocation(2, wardBedLocationIds);
        List<MonitoringDevice> allMonitoringDevices = new ArrayList<>(wardRoomMonitoringDevices.size() + wardBedMonitoringDevices.size());
        allMonitoringDevices.addAll(wardRoomMonitoringDevices);
        allMonitoringDevices.addAll(wardBedMonitoringDevices);

        // 4. 查询并组装设备的最新功能数据
        Map<String, List<VitalSignDataVo>> vitalSignDataMap = buildLatestVitalSignDataMap(allMonitoringDevices);
        Map<Long, List<MonitoringDeviceVo>> wardRoomMonitoringDeviceMap = buildLocationMonitoringDeviceMap(wardRoomMonitoringDevices, vitalSignDataMap);
        Map<Long, List<MonitoringDeviceVo>> wardBedMonitoringDeviceMap = buildLocationMonitoringDeviceMap(wardBedMonitoringDevices, vitalSignDataMap);

        // 5. 回填房间设备、病床设备
        for (WardRoomVo wardRoomVo : wardRoomVos) {
            List<MonitoringDeviceVo> wardRoomMonitoringDeviceVos = wardRoomMonitoringDeviceMap.get(wardRoomVo.getId());
            wardRoomVo.setMonitoringDeviceVos(wardRoomMonitoringDeviceVos == null ? new ArrayList<>() : wardRoomMonitoringDeviceVos);
            if (wardRoomVo.getWardBedVoList() == null || wardRoomVo.getWardBedVoList().isEmpty()) {
                continue;
            }
            for (WardBedVo wardBedVo : wardRoomVo.getWardBedVoList()) {
                if (wardBedVo == null || wardBedVo.getId() == null) {
                    if (wardBedVo != null) {
                        wardBedVo.setMonitoringDeviceVos(new ArrayList<>());
                    }
                    continue;
                }
                List<MonitoringDeviceVo> wardBedMonitoringDeviceVos = wardBedMonitoringDeviceMap.get(wardBedVo.getId());
                wardBedVo.setMonitoringDeviceVos(wardBedMonitoringDeviceVos == null ? new ArrayList<>() : wardBedMonitoringDeviceVos);
            }
        }
        return wardRoomVos;*/
        return wardRoomMapper.selectByWardFloorIdWithNur(wardFloorId);

    }

    /**
     *
     * 根据楼层ID获取房间中的智能设备及数据
     * @param wardFloorId 楼层ID
     * @return 房间列表
     */
    @Override
    public List<WardRoomVo> getWardRoomsWithMonitoringDeviceByWardFloorId(Long wardFloorId) {
        //1. 从数据库当中获取数据
        List<WardRoomVo> wardRoomVos = wardRoomMapper.getWardRoomsWithMonitoringDeviceByWardFloorId(wardFloorId);
        wardRoomVos.forEach(wardRoomVo -> {
            // 遍历的是房间数据
            List<MonitoringDeviceInfo> monitoringDeviceVos = wardRoomVo.getMonitoringDevices();
            // 房间设备所对应的设备上报的数据
            monitoringDeviceVos.forEach(monitoringDeviceInfo -> {
                String jsonStr = (String) redisTemplate.opsForHash().get(CacheConstants.IOT_MONITORING_DEVICE_LAST_DATA, monitoringDeviceInfo.getIotId());
                if(StringUtils.isEmpty(jsonStr)) {
                    return; // 跳出本次循环，并不是结束方法
                }
                List<VitalSignData> list = JSONUtil.toList(jsonStr, VitalSignData.class);
                monitoringDeviceInfo.setVitalSignDataVos(list);
            });
            // 遍历的是病床数据
            wardRoomVo.getWardBedVoList().forEach(wardBedVo -> {
                // 获取病床对应的设备列表
                wardBedVo.getMonitoringDevices().forEach(monitoringDeviceInfo -> {
                    String jsonStr = (String) redisTemplate.opsForHash().get(CacheConstants.IOT_MONITORING_DEVICE_LAST_DATA, monitoringDeviceInfo.getIotId());
                    if(StringUtils.isEmpty(jsonStr)) {
                        return; // 跳出本次循环，并不是结束方法
                    }
                    monitoringDeviceInfo.setVitalSignDataVos(JSONUtil.toList(jsonStr, VitalSignData.class));
                });
            });
        });
        return wardRoomVos;

    }

    /**
     * 根据 id 获取房间视图对象
     *
     * @param id 房间ID
     * @return 房间对象
     */
    @Override
    public WardRoomVo getWardRoomById(Long id) {
        return wardRoomMapper.getWardRoomById(id);
    }

/*    *//**
     * 按绑定位置查询固定设备
     *
     * @param physicalLocationType 物理位置类型：1房间，2病床
     * @param locationIds          绑定位置ID集合
     * @return 设备列表
     *//*
    private List<MonitoringDevice> selectFixedMonitoringDevicesByLocation(Integer physicalLocationType, Set<String> locationIds) {
        if (locationIds == null || locationIds.isEmpty()) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<MonitoringDevice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MonitoringDevice::getLocationType, 1)
                .eq(MonitoringDevice::getPhysicalLocationType, physicalLocationType)
                .in(MonitoringDevice::getBindingLocation, locationIds)
                .orderByAsc(MonitoringDevice::getId);
        return monitoringDeviceMapper.selectList(queryWrapper);
    }*/

 /*   *//**
     * 组装每个设备最新的功能数据（每个功能保留一条最新记录）
     *
     * @param monitoringDevices 设备列表
     * @return key=iotId，value=设备数据列表
     *//*
    private Map<String, List<VitalSignDataVo>> buildLatestVitalSignDataMap(List<MonitoringDevice> monitoringDevices) {
        if (monitoringDevices == null || monitoringDevices.isEmpty()) {
            return Collections.emptyMap();
        }

        Set<String> iotIds = new HashSet<>();
        Map<String, String> nicknameMap = new HashMap<>();
        for (MonitoringDevice monitoringDevice : monitoringDevices) {
            if (monitoringDevice == null || isBlank(monitoringDevice.getIotId())) {
                continue;
            }
            iotIds.add(monitoringDevice.getIotId());
            nicknameMap.put(monitoringDevice.getIotId(), monitoringDevice.getRemark());
        }
        if (iotIds.isEmpty()) {
            return Collections.emptyMap();
        }

        LambdaQueryWrapper<VitalSignData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(VitalSignData::getIotId, iotIds)
                .orderByDesc(VitalSignData::getAlarmTime)
                .orderByDesc(VitalSignData::getId);
        List<VitalSignData> dataList = vitalSignDataMapper.selectList(queryWrapper);
        if (dataList == null || dataList.isEmpty()) {
            return Collections.emptyMap();
        }

        // key1=iotId，key2=functionId，value=最新数据
        Map<String, LinkedHashMap<String, VitalSignDataVo>> latestDataMap = new HashMap<>();
        for (VitalSignData vitalSignData : dataList) {
            if (vitalSignData == null || isBlank(vitalSignData.getIotId())) {
                continue;
            }
            String functionId = isBlank(vitalSignData.getFunctionId()) ? "__default__" : vitalSignData.getFunctionId();
            LinkedHashMap<String, VitalSignDataVo> functionDataMap =
                    latestDataMap.computeIfAbsent(vitalSignData.getIotId(), key -> new LinkedHashMap<>());
            if (functionDataMap.containsKey(functionId)) {
                continue;
            }
            VitalSignDataVo vitalSignDataVo = new VitalSignDataVo();
            BeanUtils.copyProperties(vitalSignData, vitalSignDataVo);
            vitalSignDataVo.setNickname(nicknameMap.get(vitalSignData.getIotId()));
            functionDataMap.put(functionId, vitalSignDataVo);
        }

        Map<String, List<VitalSignDataVo>> result = new HashMap<>();
        latestDataMap.forEach((iotId, functionDataMap) -> result.put(iotId, new ArrayList<>(functionDataMap.values())));
        return result;
    }*/

/*    *//**
     * 按绑定位置组装设备列表
     *
     * @param monitoringDevices       设备列表
     * @param vitalSignDataMap key=iotId，value=设备数据列表
     * @return key=位置ID，value=设备列表
     *//*
    private Map<Long, List<MonitoringDeviceVo>> buildLocationMonitoringDeviceMap(List<MonitoringDevice> monitoringDevices,
                                                             Map<String, List<VitalSignDataVo>> vitalSignDataMap) {
        if (monitoringDevices == null || monitoringDevices.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<Long, List<MonitoringDeviceVo>> result = new HashMap<>();
        for (MonitoringDevice monitoringDevice : monitoringDevices) {
            Long locationId = parseLong(monitoringDevice.getBindingLocation());
            if (locationId == null) {
                continue;
            }
            MonitoringDeviceVo monitoringDeviceVo = new MonitoringDeviceVo();
            BeanUtils.copyProperties(monitoringDevice, monitoringDeviceVo);
            List<VitalSignDataVo> vitalSignDataVos = vitalSignDataMap.get(monitoringDevice.getIotId());
            monitoringDeviceVo.setVitalSignDataVos(vitalSignDataVos == null ? new ArrayList<>() : vitalSignDataVos);
            result.computeIfAbsent(locationId, key -> new ArrayList<>()).add(monitoringDeviceVo);
        }
        return result;
    }

    *//**
     * 字符串转Long，失败返回null
     *//*
    private Long parseLong(String value) {
        if (isBlank(value)) {
            return null;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    *//**
     * 判空字符串
     *//*
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }*/
}
