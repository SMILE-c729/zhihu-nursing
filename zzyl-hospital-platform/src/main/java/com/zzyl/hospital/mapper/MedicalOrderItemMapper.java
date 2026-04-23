package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.MedicalOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.vo.MedicalOrderItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 医嘱项目Mapper接口
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Mapper
public interface MedicalOrderItemMapper extends BaseMapper<MedicalOrderItem>
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
     * @param medicalOrderItem 医嘱项目
     * @return 医嘱项目集合
     */
    public List<MedicalOrderItem> selectMedicalOrderItemList(MedicalOrderItem medicalOrderItem);

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
     * 删除医嘱项目
     * 
     * @param id 医嘱项目主键
     * @return 结果
     */
    public int deleteMedicalOrderItemById(Long id);

    /**
     * 批量删除医嘱项目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMedicalOrderItemByIds(Long[] ids);

    /**
     * 查询所有医嘱项目
     * @return  医嘱项目列表
     */
    @Select("select name label, id value from medical_order_item where status = 1")
    List<MedicalOrderItemVo> getAll();
}
