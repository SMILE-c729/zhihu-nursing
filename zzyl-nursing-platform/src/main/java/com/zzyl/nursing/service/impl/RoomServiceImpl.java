package com.zzyl.nursing.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.nursing.domain.Device;
import com.zzyl.nursing.domain.DeviceData;
import com.zzyl.nursing.domain.Room;
import com.zzyl.nursing.mapper.DeviceDataMapper;
import com.zzyl.nursing.mapper.DeviceMapper;
import com.zzyl.nursing.mapper.RoomMapper;
import com.zzyl.nursing.service.IRoomService;
import com.zzyl.nursing.vo.*;
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
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceDataMapper deviceDataMapper;
    @Autowired
    private RedisTemplate<String ,String> redisTemplate;
    /**
     * 查询房间
     *
     * @param id 房间主键
     * @return 房间
     */
    @Override
    public Room selectRoomById(Long id) {
        return getById(id);
    }

    /**
     * 查询房间列表
     *
     * @param room 房间
     * @return 房间
     */
    @Override
    public List<Room> selectRoomList(Room room) {
        return roomMapper.selectRoomList(room);
    }

    /**
     * 新增房间
     *
     * @param room 房间
     * @return 结果
     */
    @Override
    public int insertRoom(Room room) {
        return save(room) ? 1 : 0;
    }

    /**
     * 修改房间
     *
     * @param room 房间
     * @return 结果
     */
    @Override
    public int updateRoom(Room room) {
        return updateById(room) ? 1 : 0;
    }

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteRoomByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 根据楼层 id 获取房间视图对象列表
     *
     * @param floorId 楼层ID
     * @return 房间列表
     */
    @Override
    public List<RoomVo> getRoomsByFloorId(Long floorId) {
        return roomMapper.selectByFloorId(floorId);
    }

    /**
     * 获取所有房间（负责老人）
     *
     * @param floorId 楼层ID
     * @return 房间列表
     */

    @Override
    public List<RoomVo> getRoomsWithNurByFloorId(Long floorId) {
        // 1. 查询楼层下房间和床位基础信息
        List<RoomVo> roomVos = roomMapper.selectByFloorId(floorId);
        if (roomVos == null || roomVos.isEmpty()) {
            return roomVos;
        }

        // 2. 收集房间和床位ID，用于按绑定位置查询设备
        Set<String> roomLocationIds = new HashSet<>();
        Set<String> bedLocationIds = new HashSet<>();
        for (RoomVo roomVo : roomVos) {
            if (roomVo.getId() != null) {
                roomLocationIds.add(String.valueOf(roomVo.getId()));
            }
            if (roomVo.getBedVoList() == null || roomVo.getBedVoList().isEmpty()) {
                continue;
            }
            for (BedVo bedVo : roomVo.getBedVoList()) {
                if (bedVo != null && bedVo.getId() != null) {
                    bedLocationIds.add(String.valueOf(bedVo.getId()));
                }
            }
        }

        // 3. 分别查询房间设备和床位设备
        List<Device> roomDevices = selectFixedDevicesByLocation(1, roomLocationIds);
        List<Device> bedDevices = selectFixedDevicesByLocation(2, bedLocationIds);
        List<Device> allDevices = new ArrayList<>(roomDevices.size() + bedDevices.size());
        allDevices.addAll(roomDevices);
        allDevices.addAll(bedDevices);

        // 4. 查询并组装设备的最新功能数据
        Map<String, List<DeviceDataVo>> deviceDataMap = buildLatestDeviceDataMap(allDevices);
        Map<Long, List<DeviceVo>> roomDeviceMap = buildLocationDeviceMap(roomDevices, deviceDataMap);
        Map<Long, List<DeviceVo>> bedDeviceMap = buildLocationDeviceMap(bedDevices, deviceDataMap);

        // 5. 回填房间设备、床位设备
        for (RoomVo roomVo : roomVos) {
            List<DeviceVo> roomDeviceVos = roomDeviceMap.get(roomVo.getId());
            roomVo.setDeviceVos(roomDeviceVos == null ? new ArrayList<>() : roomDeviceVos);
            if (roomVo.getBedVoList() == null || roomVo.getBedVoList().isEmpty()) {
                continue;
            }
            for (BedVo bedVo : roomVo.getBedVoList()) {
                if (bedVo == null || bedVo.getId() == null) {
                    if (bedVo != null) {
                        bedVo.setDeviceVos(new ArrayList<>());
                    }
                    continue;
                }
                List<DeviceVo> bedDeviceVos = bedDeviceMap.get(bedVo.getId());
                bedVo.setDeviceVos(bedDeviceVos == null ? new ArrayList<>() : bedDeviceVos);
            }
        }
        return roomVos;
    }

    /**
     *
     * 根据楼层ID获取房间中的智能设备及数据
     * @param floorId 楼层ID
     * @return 房间列表
     */
    @Override
    public List<RoomVo> getRoomsWithDeviceByFloorId(Long floorId) {
        //1. 从数据库当中获取数据
        List<RoomVo> roomVos = roomMapper.getRoomsWithDeviceByFloorId(floorId);
        roomVos.forEach(roomVo -> {
            // 遍历的是房间数据
            List<DeviceInfo> deviceVos = roomVo.getDevices();
            // 房间设备所对应的设备上报的数据
            deviceVos.forEach(deviceInfo -> {
                String jsonStr = (String) redisTemplate.opsForHash().get(CacheConstants.IOT_DEVICE_LAST_DATA, deviceInfo.getIotId());
                if(StringUtils.isEmpty(jsonStr)) {
                    return; // 跳出本次循环，并不是结束方法
                }
                List<DeviceData> list = JSONUtil.toList(jsonStr, DeviceData.class);
                deviceInfo.setDeviceDataVos(list);
            });
            // 遍历的是床位数据
            roomVo.getBedVoList().forEach(bedVo -> {
                // 获取床位对应的设备列表
                bedVo.getDevices().forEach(deviceInfo -> {
                    String jsonStr = (String) redisTemplate.opsForHash().get(CacheConstants.IOT_DEVICE_LAST_DATA, deviceInfo.getIotId());
                    if(StringUtils.isEmpty(jsonStr)) {
                        return; // 跳出本次循环，并不是结束方法
                    }
                    deviceInfo.setDeviceDataVos(JSONUtil.toList(jsonStr, DeviceData.class));
                });
            });
        });
        return roomVos;

    }

    /**
     * 根据 id 获取房间视图对象
     *
     * @param id 房间ID
     * @return 房间对象
     */
    @Override
    public RoomVo getRoomById(Long id) {
        return roomMapper.getRoomById(id);
    }

    /**
     * 按绑定位置查询固定设备
     *
     * @param physicalLocationType 物理位置类型：1房间，2床位
     * @param locationIds          绑定位置ID集合
     * @return 设备列表
     */
    private List<Device> selectFixedDevicesByLocation(Integer physicalLocationType, Set<String> locationIds) {
        if (locationIds == null || locationIds.isEmpty()) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Device::getLocationType, 1)
                .eq(Device::getPhysicalLocationType, physicalLocationType)
                .in(Device::getBindingLocation, locationIds)
                .orderByAsc(Device::getId);
        return deviceMapper.selectList(queryWrapper);
    }

    /**
     * 组装每个设备最新的功能数据（每个功能保留一条最新记录）
     *
     * @param devices 设备列表
     * @return key=iotId，value=设备数据列表
     */
    private Map<String, List<DeviceDataVo>> buildLatestDeviceDataMap(List<Device> devices) {
        if (devices == null || devices.isEmpty()) {
            return Collections.emptyMap();
        }

        Set<String> iotIds = new HashSet<>();
        Map<String, String> nicknameMap = new HashMap<>();
        for (Device device : devices) {
            if (device == null || isBlank(device.getIotId())) {
                continue;
            }
            iotIds.add(device.getIotId());
            nicknameMap.put(device.getIotId(), device.getRemark());
        }
        if (iotIds.isEmpty()) {
            return Collections.emptyMap();
        }

        LambdaQueryWrapper<DeviceData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(DeviceData::getIotId, iotIds)
                .orderByDesc(DeviceData::getAlarmTime)
                .orderByDesc(DeviceData::getId);
        List<DeviceData> dataList = deviceDataMapper.selectList(queryWrapper);
        if (dataList == null || dataList.isEmpty()) {
            return Collections.emptyMap();
        }

        // key1=iotId，key2=functionId，value=最新数据
        Map<String, LinkedHashMap<String, DeviceDataVo>> latestDataMap = new HashMap<>();
        for (DeviceData deviceData : dataList) {
            if (deviceData == null || isBlank(deviceData.getIotId())) {
                continue;
            }
            String functionId = isBlank(deviceData.getFunctionId()) ? "__default__" : deviceData.getFunctionId();
            LinkedHashMap<String, DeviceDataVo> functionDataMap =
                    latestDataMap.computeIfAbsent(deviceData.getIotId(), key -> new LinkedHashMap<>());
            if (functionDataMap.containsKey(functionId)) {
                continue;
            }
            DeviceDataVo deviceDataVo = new DeviceDataVo();
            BeanUtils.copyProperties(deviceData, deviceDataVo);
            deviceDataVo.setNickname(nicknameMap.get(deviceData.getIotId()));
            functionDataMap.put(functionId, deviceDataVo);
        }

        Map<String, List<DeviceDataVo>> result = new HashMap<>();
        latestDataMap.forEach((iotId, functionDataMap) -> result.put(iotId, new ArrayList<>(functionDataMap.values())));
        return result;
    }

    /**
     * 按绑定位置组装设备列表
     *
     * @param devices       设备列表
     * @param deviceDataMap key=iotId，value=设备数据列表
     * @return key=位置ID，value=设备列表
     */
    private Map<Long, List<DeviceVo>> buildLocationDeviceMap(List<Device> devices,
                                                             Map<String, List<DeviceDataVo>> deviceDataMap) {
        if (devices == null || devices.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<Long, List<DeviceVo>> result = new HashMap<>();
        for (Device device : devices) {
            Long locationId = parseLong(device.getBindingLocation());
            if (locationId == null) {
                continue;
            }
            DeviceVo deviceVo = new DeviceVo();
            BeanUtils.copyProperties(device, deviceVo);
            List<DeviceDataVo> deviceDataVos = deviceDataMap.get(device.getIotId());
            deviceVo.setDeviceDataVos(deviceDataVos == null ? new ArrayList<>() : deviceDataVos);
            result.computeIfAbsent(locationId, key -> new ArrayList<>()).add(deviceVo);
        }
        return result;
    }

    /**
     * 字符串转Long，失败返回null
     */
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

    /**
     * 判空字符串
     */
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
