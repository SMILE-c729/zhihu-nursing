package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import com.zzyl.hospital.dto.QueryParm;
import com.zzyl.hospital.vo.MedicalOrderItemVo;
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
import com.zzyl.hospital.domain.MedicalOrderItem;
import com.zzyl.hospital.service.IMedicalOrderItemService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 医嘱项目Controller
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Api(tags = "医嘱项目管理")
@RestController
@RequestMapping("/hospital/project")
public class MedicalOrderItemController extends BaseController
{
    @Autowired
    private IMedicalOrderItemService medicalOrderItemService;

    /**
     * 查询医嘱项目列表
     */
    @ApiOperation("查询医嘱项目列表")
    @PreAuthorize("@ss.hasPermi('hospital:project:list')")
    @GetMapping("/list")
    public TableDataInfo<List<MedicalOrderItem>> list(@ApiParam("查询条件对象") QueryParm queryParm)
    {
        List<MedicalOrderItem> list = medicalOrderItemService.selectMedicalOrderItemList(queryParm);
        return getDataTable(list);
    }

    /**
     * 导出医嘱项目列表
     */
    @ApiOperation("导出医嘱项目列表")
    @PreAuthorize("@ss.hasPermi('hospital:project:export')")
    @Log(title = "医嘱项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, @RequestBody QueryParm queryParm)
    {
        List<MedicalOrderItem> list = medicalOrderItemService.selectMedicalOrderItemList(queryParm);
        ExcelUtil<MedicalOrderItem> util = new ExcelUtil<MedicalOrderItem>(MedicalOrderItem.class);
        util.exportExcel(response, list, "医嘱项目数据");
    }

    /**
     * 获取医嘱项目详细信息
     */
    @ApiOperation("获取医嘱项目详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:project:query')")
    @GetMapping(value = "/{id}")
    public R<MedicalOrderItem> getInfo(@PathVariable("id") @ApiParam("医嘱项目ID") Long id)
    {
        return R.ok(medicalOrderItemService.selectMedicalOrderItemById(id));
    }

    /**
     * 新增医嘱项目
     */
    @ApiOperation("新增医嘱项目")
    @PreAuthorize("@ss.hasPermi('hospital:project:add')")
    @Log(title = "医嘱项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的医嘱项目对象") MedicalOrderItem medicalOrderItem)
    {
        return toAjax(medicalOrderItemService.insertMedicalOrderItem(medicalOrderItem));
    }

    /**
     * 修改医嘱项目
     */
    @ApiOperation("修改医嘱项目")
    @PreAuthorize("@ss.hasPermi('hospital:project:edit')")
    @Log(title = "医嘱项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的医嘱项目对象") MedicalOrderItem medicalOrderItem)
    {
        return toAjax(medicalOrderItemService.updateMedicalOrderItem(medicalOrderItem));
    }

    /**
     * 删除医嘱项目
     */
    @ApiOperation("删除医嘱项目")
    @PreAuthorize("@ss.hasPermi('hospital:project:remove')")
    @Log(title = "医嘱项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的医嘱项目ID") Long[] ids)
    {
        return toAjax(medicalOrderItemService.deleteMedicalOrderItemByIds(ids));
    }

    @GetMapping("/all")
    public AjaxResult getAll() {
        List<MedicalOrderItemVo> list = medicalOrderItemService.getAll();
        return AjaxResult.success(list);
    }
}
