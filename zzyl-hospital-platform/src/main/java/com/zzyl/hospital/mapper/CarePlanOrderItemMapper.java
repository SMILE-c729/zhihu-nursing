package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.CarePlanOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.vo.CarePlanOrderItemVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 照护方案和项目关联Mapper接口
 * 
 * @author alexis
 * @date 2025-06-08
 */
@Mapper
public interface CarePlanOrderItemMapper extends BaseMapper<CarePlanOrderItem>
{
    /**
     * 查询照护方案和项目关联
     * 
     * @param id 照护方案和项目关联主键
     * @return 照护方案和项目关联
     */
    public CarePlanOrderItem selectCarePlanOrderItemById(Long id);

    /**
     * 查询照护方案和项目关联列表
     * 
     * @param carePlanOrderItem 照护方案和项目关联
     * @return 照护方案和项目关联集合
     */
    public List<CarePlanOrderItem> selectCarePlanOrderItemList(CarePlanOrderItem carePlanOrderItem);

    /**
     * 新增照护方案和项目关联
     * 
     * @param carePlanOrderItem 照护方案和项目关联
     * @return 结果
     */
    public int insertCarePlanOrderItem(CarePlanOrderItem carePlanOrderItem);

    /**
     * 修改照护方案和项目关联
     * 
     * @param carePlanOrderItem 照护方案和项目关联
     * @return 结果
     */
    public int updateCarePlanOrderItem(CarePlanOrderItem carePlanOrderItem);

    /**
     * 删除照护方案和项目关联
     * 
     * @param id 照护方案和项目关联主键
     * @return 结果
     */
    public int deleteCarePlanOrderItemById(Long id);

    /**
     * 批量删除照护方案和项目关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCarePlanOrderItemByIds(Long[] ids);

    int batchInsert(@Param("list") List<CarePlanOrderItem> projectPlans, @Param("carePlanId") Long carePlanId);

    /**
     * 根据照护方案ID查询关联的医嘱项目列表
     * @param carePlanId    照护方案id
     * @return
     */
    List<CarePlanOrderItemVo> selectByCarePlanId(@Param("carePlanId") Long carePlanId);

    @Delete("delete from care_plan_order_item where care_plan_id = #{carePlanId}")
    void deleteByCarePlanId(@Param("carePlanId") Long carePlanId);
}
