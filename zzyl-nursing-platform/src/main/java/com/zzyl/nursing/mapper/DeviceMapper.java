package com.zzyl.nursing.mapper;

import java.util.List;

import com.zzyl.nursing.domain.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.nursing.domain.DeviceData;
import lombok.Getter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 设备Mapper接口
 *
 * @author alexis
 * @date 2026-02-11
 */
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
    /**
     * 查询设备
     *
     * @param id 设备主键
     * @return 设备
     */
    public Device selectDeviceById(Long id);

    /**
     * 查询设备列表
     *
     * @param device 设备
     * @return 设备集合
     */
    public List<Device> selectDeviceList(Device device);

    /**
     * 新增设备
     *
     * @param device 设备
     * @return 结果
     */
    public int insertDevice(Device device);

    /**
     * 修改设备
     *
     * @param device 设备
     * @return 结果
     */
    public int updateDevice(Device device);

    /**
     * 删除设备
     *
     * @param id 设备主键
     * @return 结果
     */
    public int deleteDeviceById(Long id);

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceByIds(Long[] ids);

    /**
     * 根据设备ID查询设备
     *
     * @param deviceId 设备ID
     * @return 设备
     */
    @Select("SELECT * FROM device WHERE iot_id = #{deviceId}")
    Device selectDeviceByDeviceId(String deviceId);
}
