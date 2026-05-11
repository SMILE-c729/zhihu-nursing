package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.WarningRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警规则Mapper接口
 * 
 * @author alexis
 * @date 2025-07-14
 */
@Mapper
public interface WarningRuleMapper extends BaseMapper<WarningRule>
{
    /**
     * 查询预警规则
     * 
     * @param id 预警规则主键
     * @return 预警规则
     */
    public WarningRule selectWarningRuleById(Long id);

    /**
     * 查询预警规则列表
     * 
     * @param warningRule 预警规则
     * @return 预警规则集合
     */
    public List<WarningRule> selectWarningRuleList(WarningRule warningRule);

    /**
     * 新增预警规则
     * 
     * @param warningRule 预警规则
     * @return 结果
     */
    public int insertWarningRule(WarningRule warningRule);

    /**
     * 修改预警规则
     * 
     * @param warningRule 预警规则
     * @return 结果
     */
    public int updateWarningRule(WarningRule warningRule);

    /**
     * 删除预警规则
     * 
     * @param id 预警规则主键
     * @return 结果
     */
    public int deleteWarningRuleById(Long id);

    /**
     * 批量删除预警规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWarningRuleByIds(Long[] ids);
}
