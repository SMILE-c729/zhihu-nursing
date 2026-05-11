package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.CarePlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 照护方案Mapper接口
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Mapper
public interface CarePlanMapper extends BaseMapper<CarePlan>
{
    /**
     * 查询照护方案
     * 
     * @param id 照护方案主键
     * @return 照护方案
     */
    public CarePlan selectCarePlanById(Long id);

    /**
     * 查询照护方案列表
     * 
     * @param carePlan 照护方案
     * @return 照护方案集合
     */
    public List<CarePlan> selectCarePlanList(CarePlan carePlan);

    /**
     * 新增照护方案
     * 
     * @param carePlan 照护方案
     * @return 结果
     */
    public int insertCarePlan(CarePlan carePlan);

    /**
     * 修改照护方案
     * 
     * @param carePlan 照护方案
     * @return 结果
     */
    public int updateCarePlan(CarePlan carePlan);

    /**
     * 删除照护方案
     * 
     * @param id 照护方案主键
     * @return 结果
     */
    public int deleteCarePlanById(Long id);

    /**
     * 批量删除照护方案
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarePlanByIds(Long[] ids);
}
