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
import java.time.LocalDateTime;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.WarningData;
import com.zzyl.hospital.service.IWarningDataService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 预警数据Controller
 * 
 * @author alexis
 * @date 2026-02-24
 */
@Api(tags = "预警数据管理")
@RestController
@RequestMapping("/hospital/warningData")
public class WarningDataController extends BaseController
{
    @Autowired
    private IWarningDataService warningDataService;

    /**
     * 查询预警数据列表
     */
    @ApiOperation("查询预警数据列表")
    @PreAuthorize("@ss.hasPermi('hospital:data:list')")
    @GetMapping("/list")
    public TableDataInfo<List<WarningData>> list(@ApiParam("查询条件对象") WarningData warningData)
    {
        startPage();
        List<WarningData> list = warningDataService.selectWarningDataList(warningData);
        return getDataTable(list);
    }

    /**
     * 导出预警数据列表
     */
    @ApiOperation("导出预警数据列表")
    @PreAuthorize("@ss.hasPermi('hospital:data:export')")
    @Log(title = "预警数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, WarningData warningData)
    {
        List<WarningData> list = warningDataService.selectWarningDataList(warningData);
        ExcelUtil<WarningData> util = new ExcelUtil<WarningData>(WarningData.class);
        util.exportExcel(response, list, "预警数据数据");
    }

    /**
     * 获取预警数据详细信息
     */
    @ApiOperation("获取预警数据详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:data:query')")
    @GetMapping(value = "/{id}")
    public R<WarningData> getInfo(@PathVariable("id") @ApiParam("预警数据ID") Long id)
    {
        return R.ok(warningDataService.selectWarningDataById(id));
    }

    /**
     * 新增预警数据
     */
    @ApiOperation("新增预警数据")
    @PreAuthorize("@ss.hasPermi('hospital:data:add')")
    @Log(title = "预警数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的预警数据对象") WarningData warningData)
    {
        return toAjax(warningDataService.insertWarningData(warningData));
    }

    /**
     * 修改预警数据
     */
    @ApiOperation("修改预警数据")
    @PreAuthorize("@ss.hasPermi('hospital:data:edit')")
    @Log(title = "预警数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的预警数据对象") WarningData warningData)
    {
        return toAjax(warningDataService.updateWarningData(warningData));
    }

    /**
     * 处理预警数据，兼容前端历史接口。
     */
    @ApiOperation("处理预警数据")
    @PreAuthorize("@ss.hasPermi('hospital:data:edit')")
    @Log(title = "预警数据", businessType = BusinessType.UPDATE)
    @PutMapping("/handleAlertData")
    public AjaxResult handleAlertData(@RequestBody WarningData warningData)
    {
        warningData.setStatus(1);
        warningData.setProcessingTime(LocalDateTime.now());
        return toAjax(warningDataService.updateWarningData(warningData));
    }

    /**
     * 删除预警数据
     */
    @ApiOperation("删除预警数据")
    @PreAuthorize("@ss.hasPermi('hospital:data:remove')")
    @Log(title = "预警数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的预警数据ID") Long[] ids)
    {
        return toAjax(warningDataService.deleteWarningDataByIds(ids));
    }
}
