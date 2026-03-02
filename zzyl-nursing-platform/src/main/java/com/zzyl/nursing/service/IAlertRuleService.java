package com.zzyl.nursing.service;

import java.util.List;
import com.zzyl.nursing.domain.AlertRule;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 报警规则功能Service接口
 * 
 * @author alexis
 * @date 2026-02-24
 */
public interface IAlertRuleService extends IService<AlertRule>
{
    /**
     * 查询报警规则功能
     * 
     * @param id 报警规则功能主键
     * @return 报警规则功能
     */
    public AlertRule selectAlertRuleById(Long id);

    /**
     * 查询报警规则功能列表
     * 
     * @param alertRule 报警规则功能
     * @return 报警规则功能集合
     */
    public List<AlertRule> selectAlertRuleList(AlertRule alertRule);

    /**
     * 新增报警规则功能
     * 
     * @param alertRule 报警规则功能
     * @return 结果
     */
    public int insertAlertRule(AlertRule alertRule);

    /**
     * 修改报警规则功能
     * 
     * @param alertRule 报警规则功能
     * @return 结果
     */
    public int updateAlertRule(AlertRule alertRule);

    /**
     * 批量删除报警规则功能
     * 
     * @param ids 需要删除的报警规则功能主键集合
     * @return 结果
     */
    public int deleteAlertRuleByIds(Long[] ids);

    /**
     * 删除报警规则功能信息
     * 
     * @param id 报警规则功能主键
     * @return 结果
     */
    public int deleteAlertRuleById(Long id);

    /**
     * 报警规则功能过滤
     */
    void alertFilter();

}
