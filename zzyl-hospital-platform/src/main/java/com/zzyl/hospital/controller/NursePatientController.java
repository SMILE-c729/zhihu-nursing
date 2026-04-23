package com.zzyl.hospital.controller;

import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.hospital.domain.NursePatient;
import com.zzyl.hospital.dto.NursePatientDto;
import com.zzyl.hospital.service.INursePatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 责任护士患者关联Controller
 * 
 * @author ruoyi
 * @date 2024-05-28
 */
@RestController
@RequestMapping("/patient/hospitalPatient")
@Api(tags = "责任患者管理")
public class NursePatientController extends BaseController
{
    @Autowired
    private INursePatientService nursePatientService;

    @PostMapping("/setNursing")
    @ApiOperation("设置责任护士")
    public AjaxResult setNursePatient(@RequestBody List<NursePatientDto> nursePatientDtos)
    {
       return AjaxResult.success(nursePatientService.setNursePatient(nursePatientDtos));
    }

    /**
     * 查询责任护士患者关联列表
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:list')")
    @GetMapping("/list")
    @ApiOperation("查询责任护士患者关联列表")
    public TableDataInfo list(NursePatient nursePatient)
    {
        startPage();
        List<NursePatient> list = nursePatientService.selectNursePatientList(nursePatient);
        return getDataTable(list);
    }

    /**
     * 导出责任护士患者关联列表
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:export')")
    @Log(title = "责任护士患者关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursePatient nursePatient)
    {
        List<NursePatient> list = nursePatientService.selectNursePatientList(nursePatient);
        ExcelUtil<NursePatient> util = new ExcelUtil<NursePatient>(NursePatient.class);
        util.exportExcel(response, list, "责任护士患者关联数据");
    }

    /**
     * 获取责任护士患者关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取责任护士患者关联详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nursePatientService.selectNursePatientById(id));
    }

    /**
     * 新增责任护士患者关联
     */
    @PreAuthorize("@ss.hasPermi('patient:patient:add')")
    @Log(title = "责任护士患者关联", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增责任护士患者关联")
    public AjaxResult add(@RequestBody NursePatient nursePatient)
    {
        return toAjax(nursePatientService.insertNursePatient(nursePatient));
    }

    /**
     * 修改责任护士患者关联
     */
    @ApiOperation("修改责任护士患者关联")
    @PreAuthorize("@ss.hasPermi('patient:patient:edit')")
    @Log(title = "责任护士患者关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NursePatient nursePatient)
    {
        return toAjax(nursePatientService.updateNursePatient(nursePatient));
    }

    /**
     * 删除责任护士患者关联
     */
    @ApiOperation("删除责任护士患者关联")
    @PreAuthorize("@ss.hasPermi('patient:patient:remove')")
    @Log(title = "责任护士患者关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nursePatientService.deleteNursePatientByIds(ids));
    }
}
