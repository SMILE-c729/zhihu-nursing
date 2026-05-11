package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.WarningData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警数据Mapper接口
 * 
 * @author alexis
 * @date 2025-07-16
 */
@Mapper
public interface WarningDataMapper extends BaseMapper<WarningData>
{
    /**
     * 查询预警数据
     * 
     * @param id 预警数据主键
     * @return 预警数据
     */
    public WarningData selectWarningDataById(Long id);

    /**
     * 查询预警数据列表
     * 
     * @param warningData 预警数据
     * @return 预警数据集合
     */
    public List<WarningData> selectWarningDataList(WarningData warningData);

    /**
     * 新增预警数据
     * 
     * @param warningData 预警数据
     * @return 结果
     */
    public int insertWarningData(WarningData warningData);

    /**
     * 修改预警数据
     * 
     * @param warningData 预警数据
     * @return 结果
     */
    public int updateWarningData(WarningData warningData);

    /**
     * 删除预警数据
     * 
     * @param id 预警数据主键
     * @return 结果
     */
    public int deleteWarningDataById(Long id);

    /**
     * 批量删除预警数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWarningDataByIds(Long[] ids);
}
