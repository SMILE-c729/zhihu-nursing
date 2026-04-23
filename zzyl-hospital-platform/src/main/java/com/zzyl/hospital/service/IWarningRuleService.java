package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.WarningRule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 预警规则功能Service接口
 * 
 * @author alexis
 * @date 2026-02-24
 */
public interface IWarningRuleService extends IService<WarningRule>
{
    /**
     * 查询预警规则功能
     * 
     * @param id 预警规则功能主键
     * @return 预警规则功能
     */
    public WarningRule selectWarningRuleById(Long id);

    /**
     * 查询预警规则功能列表
     * 
     * @param warningRule 预警规则功能
     * @return 预警规则功能集合
     */
    public List<WarningRule> selectWarningRuleList(WarningRule warningRule);

    /**
     * 新增预警规则功能
     * 
     * @param warningRule 预警规则功能
     * @return 结果
     */
    public int insertWarningRule(WarningRule warningRule);

    /**
     * 修改预警规则功能
     * 
     * @param warningRule 预警规则功能
     * @return 结果
     */
    public int updateWarningRule(WarningRule warningRule);

    /**
     * 批量删除预警规则功能
     * 
     * @param ids 需要删除的预警规则功能主键集合
     * @return 结果
     */
    public int deleteWarningRuleByIds(Long[] ids);

    /**
     * 删除预警规则功能信息
     * 
     * @param id 预警规则功能主键
     * @return 结果
     */
    public int deleteWarningRuleById(Long id);

    /**
     * 预警规则功能过滤
     */
    void warningFilter();

}
