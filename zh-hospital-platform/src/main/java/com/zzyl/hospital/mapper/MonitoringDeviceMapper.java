package com.zzyl.hospital.mapper;

import java.util.List;

import com.zzyl.hospital.domain.MonitoringDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.domain.VitalSignData;
import lombok.Getter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 设备Mapper接口
 *
 * @author alexis
 * @date 2026-02-11
 */
@Mapper
public interface MonitoringDeviceMapper extends BaseMapper<MonitoringDevice> {
    /**
     * 查询设备
     *
     * @param id 设备主键
     * @return 设备
     */
    public MonitoringDevice selectMonitoringDeviceById(Long id);

    /**
     * 查询设备列表
     *
     * @param monitoringDevice 设备
     * @return 设备集合
     */
    public List<MonitoringDevice> selectMonitoringDeviceList(MonitoringDevice monitoringDevice);

    /**
     * 新增设备
     *
     * @param monitoringDevice 设备
     * @return 结果
     */
    public int insertMonitoringDevice(MonitoringDevice monitoringDevice);

    /**
     * 修改设备
     *
     * @param monitoringDevice 设备
     * @return 结果
     */
    public int updateMonitoringDevice(MonitoringDevice monitoringDevice);

    /**
     * 删除设备
     *
     * @param id 设备主键
     * @return 结果
     */
    public int deleteMonitoringDeviceById(Long id);

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMonitoringDeviceByIds(Long[] ids);

    /**
     * 根据设备ID查询设备
     *
     * @param monitoringDeviceId 设备ID
     * @return 设备
     */
    @Select("SELECT * FROM monitoring_device WHERE iot_id = #{monitoringDeviceId}")
    MonitoringDevice selectMonitoringDeviceByMonitoringDeviceId(String monitoringDeviceId);

    /**
     * 根据随身设备id查询患者关联的护理人员id列表
     * @param iotId 设备id
     * @return  护理人员列表
     */
    List<Long> selectNurseIdsByIotIdWithPatient(@Param("iotId") String iotId);

    /**
     * 根据固定设备id查询患者关联的护理人员id列表(床关联设备)
     * @param iotId 设备id
     * @return  护理人员列表
     */
    List<Long> selectNurseIdsByIotIdWithWardBed(@Param("iotId") String iotId);
}
