package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.hospital.domain.CareTask;
import com.zzyl.hospital.service.ICareTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 照护任务Controller
 */
@Api(tags = "照护任务管理")
@RestController
@RequestMapping("/hospital/task")
public class CareTaskController extends BaseController {
    @Autowired
    private ICareTaskService careTaskService;

    @ApiOperation("查询照护任务列表")
    @PreAuthorize("@ss.hasPermi('hospital:task:list')")
    @GetMapping("/list")
    public TableDataInfo<List<CareTask>> list(@ApiParam("查询条件对象") CareTask careTask) {
        startPage();
        List<CareTask> list = careTaskService.selectCareTaskList(careTask);
        return getDataTable(list);
    }

    @ApiOperation("导出照护任务列表")
    @PreAuthorize("@ss.hasPermi('hospital:task:export')")
    @Log(title = "照护任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CareTask careTask) {
        List<CareTask> list = careTaskService.selectCareTaskList(careTask);
        ExcelUtil<CareTask> util = new ExcelUtil<>(CareTask.class);
        util.exportExcel(response, list, "照护任务数据");
    }

    @ApiOperation("获取照护任务详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:task:query')")
    @GetMapping("/{id}")
    public R<CareTask> getInfo(@PathVariable("id") Long id) {
        return R.ok(careTaskService.selectCareTaskById(id));
    }

    @ApiOperation("修改照护任务")
    @PreAuthorize("@ss.hasPermi('hospital:task:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody CareTask careTask) {
        return toAjax(careTaskService.updateCareTask(careTask));
    }

    @ApiOperation("取消照护任务")
    @PreAuthorize("@ss.hasPermi('hospital:task:edit')")
    @PutMapping("/cancel")
    public AjaxResult cancel(@RequestBody CareTask careTask) {
        return toAjax(careTaskService.cancelCareTask(careTask));
    }

    @ApiOperation("执行照护任务")
    @PreAuthorize("@ss.hasPermi('hospital:task:edit')")
    @PutMapping("/do")
    public AjaxResult execute(@RequestBody CareTask careTask) {
        return toAjax(careTaskService.executeCareTask(careTask));
    }

    @ApiOperation("修改照护任务执行时间")
    @PreAuthorize("@ss.hasPermi('hospital:task:edit')")
    @PutMapping("/updateTime")
    public AjaxResult updateTime(@RequestBody CareTask careTask) {
        return toAjax(careTaskService.updateCareTaskTime(careTask));
    }
}
