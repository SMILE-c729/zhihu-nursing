package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.CareLevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.vo.CareLevelVo;

/**
 * 护理级别Service接口
 * 
 * @author alexis
 * @date 2025-06-02
 */
public interface ICareLevelService extends IService<CareLevel>
{
    /**
     * 查询护理级别
     * 
     * @param id 护理级别主键
     * @return 护理级别
     */
    public CareLevel selectCareLevelById(Long id);

    /**
     * 查询护理级别列表
     * 
     * @param careLevel 护理级别
     * @return 护理级别集合
     */
    public List<CareLevel> selectCareLevelList(CareLevel careLevel);

    /**
     * 新增护理级别
     * 
     * @param careLevel 护理级别
     * @return 结果
     */
    public int insertCareLevel(CareLevel careLevel);

    /**
     * 修改护理级别
     * 
     * @param careLevel 护理级别
     * @return 结果
     */
    public int updateCareLevel(CareLevel careLevel);

    /**
     * 批量删除护理级别
     * 
     * @param ids 需要删除的护理级别主键集合
     * @return 结果
     */
    public int deleteCareLevelByIds(Long[] ids);

    /**
     * 删除护理级别信息
     * 
     * @param id 护理级别主键
     * @return 结果
     */
    public int deleteCareLevelById(Long id);

    /**
     * 查询护理级别Vo列表
     * @param careLevel  条件
     * @return  结果
     */
    List<CareLevelVo> selectCareLevelVoList(CareLevel careLevel);
}
