package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.VitalSignData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备数据Mapper接口
 * 
 * @author alexis
 * @date 2026-02-22
 */
@Mapper
public interface VitalSignDataMapper extends BaseMapper<VitalSignData>
{
    /**
     * 查询设备数据
     * 
     * @param id 设备数据主键
     * @return 设备数据
     */
    public VitalSignData selectVitalSignDataById(Long id);

    /**
     * 查询设备数据列表
     * 
     * @param vitalSignData 设备数据
     * @return 设备数据集合
     */
    public List<VitalSignData> selectVitalSignDataList(VitalSignData vitalSignData);

    /**
     * 新增设备数据
     * 
     * @param vitalSignData 设备数据
     * @return 结果
     */
    public int insertVitalSignData(VitalSignData vitalSignData);

    /**
     * 修改设备数据
     * 
     * @param vitalSignData 设备数据
     * @return 结果
     */
    public int updateVitalSignData(VitalSignData vitalSignData);

    /**
     * 删除设备数据
     * 
     * @param id 设备数据主键
     * @return 结果
     */
    public int deleteVitalSignDataById(Long id);

    /**
     * 批量删除设备数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVitalSignDataByIds(Long[] ids);
}
