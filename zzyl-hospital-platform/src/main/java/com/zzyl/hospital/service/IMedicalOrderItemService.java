package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.MedicalOrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.dto.QueryParm;
import com.zzyl.hospital.vo.MedicalOrderItemVo;

/**
 * 医嘱项目Service接口
 * 
 * @author alexis
 * @date 2025-06-02
 */
public interface IMedicalOrderItemService extends IService<MedicalOrderItem>
{
    /**
     * 查询医嘱项目
     * 
     * @param id 医嘱项目主键
     * @return 医嘱项目
     */
    public MedicalOrderItem selectMedicalOrderItemById(Long id);

    /**
     * 查询医嘱项目列表
     * 
     * @param queryParm 医嘱项目
     * @return 医嘱项目集合
     */
    public List<MedicalOrderItem> selectMedicalOrderItemList(QueryParm queryParm);

    /**
     * 新增医嘱项目
     * 
     * @param medicalOrderItem 医嘱项目
     * @return 结果
     */
    public int insertMedicalOrderItem(MedicalOrderItem medicalOrderItem);

    /**
     * 修改医嘱项目
     * 
     * @param medicalOrderItem 医嘱项目
     * @return 结果
     */
    public int updateMedicalOrderItem(MedicalOrderItem medicalOrderItem);

    /**
     * 批量删除医嘱项目
     * 
     * @param ids 需要删除的医嘱项目主键集合
     * @return 结果
     */
    public int deleteMedicalOrderItemByIds(Long[] ids);

    /**
     * 删除医嘱项目信息
     * 
     * @param id 医嘱项目主键
     * @return 结果
     */
    public int deleteMedicalOrderItemById(Long id);

    /**
     * 查询所有医嘱项目
     * @return  医嘱项目列表
     */
    List<MedicalOrderItemVo> getAll();
}
