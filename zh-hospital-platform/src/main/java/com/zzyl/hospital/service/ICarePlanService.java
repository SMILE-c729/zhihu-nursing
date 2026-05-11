package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.CarePlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.dto.CarePlanDto;
import com.zzyl.hospital.vo.CarePlanVo;

/**
 * 照护方案Service接口
 * 
 * @author alexis
 * @date 2025-06-02
 */
public interface ICarePlanService extends IService<CarePlan>
{
    /**
     * 查询照护方案
     * 
     * @param id 照护方案主键
     * @return 照护方案
     */
    public CarePlanVo selectCarePlanById(Long id);

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
     * @param dto 照护方案
     * @return 结果
     */
    public int insertCarePlan(CarePlanDto dto);

    /**
     * 修改照护方案
     * 
     * @param dto 照护方案
     * @return 结果
     */
    public int updateCarePlan(CarePlanDto dto);

    /**
     * 批量删除照护方案
     * 
     * @param ids 需要删除的照护方案主键集合
     * @return 结果
     */
    public int deleteCarePlanByIds(Long[] ids);

    /**
     * 删除照护方案信息
     * 
     * @param id 照护方案主键
     * @return 结果
     */
    public int deleteCarePlanById(Long id);


    /**
     * 查询所有照护方案
     * @return 照护方案列表
     */
    List<CarePlan> getAllCarePlans();
}
