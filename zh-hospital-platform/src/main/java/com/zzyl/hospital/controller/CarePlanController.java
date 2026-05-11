package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import com.zzyl.hospital.dto.CarePlanDto;
import com.zzyl.hospital.vo.CarePlanVo;
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
import com.zzyl.hospital.domain.CarePlan;
import com.zzyl.hospital.service.ICarePlanService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 照护方案Controller
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Api(tags = "照护方案管理")
@RestController
@RequestMapping("/hospital/plan")
public class CarePlanController extends BaseController
{
    @Autowired
    private ICarePlanService carePlanService;

    /**
     * 查询照护方案列表
     */
    @ApiOperation("查询照护方案列表")
    @PreAuthorize("@ss.hasPermi('hospital:plan:list')")
    @GetMapping("/list")
    public TableDataInfo<List<CarePlan>> list(@ApiParam("查询条件对象") CarePlan carePlan)
    {
        startPage();
        List<CarePlan> list = carePlanService.selectCarePlanList(carePlan);
        return getDataTable(list);
    }

    /**
     * 导出照护方案列表
     */
    @ApiOperation("导出照护方案列表")
    @PreAuthorize("@ss.hasPermi('hospital:plan:export')")
    @Log(title = "照护方案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, CarePlan carePlan)
    {
        List<CarePlan> list = carePlanService.selectCarePlanList(carePlan);
        ExcelUtil<CarePlan> util = new ExcelUtil<CarePlan>(CarePlan.class);
        util.exportExcel(response, list, "照护方案数据");
    }

    /**
     * 获取照护方案详细信息
     */
    @ApiOperation("获取照护方案详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:plan:query')")
    @GetMapping(value = "/{id}")
    public R<CarePlanVo> getInfo(@PathVariable("id") @ApiParam("照护方案ID") Long id)
    {
        return R.ok(carePlanService.selectCarePlanById(id));
    }

    /**
     * 新增照护方案
     */
    @ApiOperation("新增照护方案")
    @PreAuthorize("@ss.hasPermi('hospital:plan:add')")
    @Log(title = "照护方案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的照护方案对象") CarePlanDto dto)
    {
        return toAjax(carePlanService.insertCarePlan(dto));
    }

    /**
     * 修改照护方案
     */
    @ApiOperation("修改照护方案")
    @PreAuthorize("@ss.hasPermi('hospital:plan:edit')")
    @Log(title = "照护方案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的照护方案对象") CarePlanDto dto)
    {
        return toAjax(carePlanService.updateCarePlan(dto));
    }

    /**
     * 删除照护方案
     */
    @ApiOperation("删除照护方案")
    @PreAuthorize("@ss.hasPermi('hospital:plan:remove')")
    @Log(title = "照护方案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的照护方案ID") Long id)
    {
        return toAjax(carePlanService.deleteCarePlanById(id));
    }

    /**
     * 查询所有照护方案
     */
    @GetMapping("/all")
    @ApiOperation(value = "获取所有照护方案")
    public R<List<CarePlan>> listAll()
    {
        return R.ok(carePlanService.getAllCarePlans());
    }
}
