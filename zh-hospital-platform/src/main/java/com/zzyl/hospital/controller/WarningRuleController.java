package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.WarningRule;
import com.zzyl.hospital.service.IWarningRuleService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 预警规则功能Controller
 * 
 * @author alexis
 * @date 2026-02-24
 */
@Api(tags = "预警规则管理")
@RestController
@RequestMapping("/hospital/warningRule")
public class WarningRuleController extends BaseController
{
    @Autowired
    private IWarningRuleService warningRuleService;

    /**
     * 查询预警规则功能列表
     */
    @ApiOperation("查询预警规则功能列表")
    @PreAuthorize("@ss.hasPermi('hospital:warningRule:list')")
    @GetMapping("/list")
    public TableDataInfo<List<WarningRule>> list(@ApiParam("查询条件对象") WarningRule warningRule)
    {
        startPage();
        List<WarningRule> list = warningRuleService.selectWarningRuleList(warningRule);
        return getDataTable(list);
    }

    /**
     * 导出预警规则功能列表
     */
    @ApiOperation("导出预警规则功能列表")
    @PreAuthorize("@ss.hasPermi('hospital:warningRule:export')")
    @Log(title = "预警规则功能", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, WarningRule warningRule)
    {
        List<WarningRule> list = warningRuleService.selectWarningRuleList(warningRule);
        ExcelUtil<WarningRule> util = new ExcelUtil<WarningRule>(WarningRule.class);
        util.exportExcel(response, list, "预警规则功能数据");
    }

    /**
     * 获取预警规则功能详细信息
     */
    @ApiOperation("获取预警规则功能详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:warningRule:query')")
    @GetMapping(value = "/{id}")
    public R<WarningRule> getInfo(@PathVariable("id") @ApiParam("预警规则功能ID") Long id)
    {
        return R.ok(warningRuleService.selectWarningRuleById(id));
    }

    /**
     * 新增预警规则功能
     */
    @ApiOperation("新增预警规则功能")
    @PreAuthorize("@ss.hasPermi('hospital:warningRule:add')")
    @Log(title = "预警规则功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的预警规则功能对象") WarningRule warningRule)
    {
        return toAjax(warningRuleService.insertWarningRule(warningRule));
    }

    /**
     * 修改预警规则功能
     */
    @ApiOperation("修改预警规则功能")
    @PreAuthorize("@ss.hasPermi('hospital:warningRule:edit')")
    @Log(title = "预警规则功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的预警规则功能对象") WarningRule warningRule)
    {
        return toAjax(warningRuleService.updateWarningRule(warningRule));
    }

    /**
     * 删除预警规则功能
     */
    @ApiOperation("删除预警规则功能")
    @PreAuthorize("@ss.hasPermi('hospital:warningRule:remove')")
    @Log(title = "预警规则功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的预警规则功能ID") Long[] ids)
    {
        return toAjax(warningRuleService.deleteWarningRuleByIds(ids));
    }
}
