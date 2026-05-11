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
import com.zzyl.hospital.domain.Patient;
import com.zzyl.hospital.service.IPatientService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 患者Controller
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Api(tags = "患者管理")
@RestController
@RequestMapping("/hospital/patient")
public class PatientController extends BaseController
{
    @Autowired
    private IPatientService patientService;

    /**
     * 查询患者列表
     */
    @ApiOperation("查询患者列表")
    @PreAuthorize("@ss.hasPermi('hospital:patient:list')")
    @GetMapping("/list")
    public TableDataInfo<List<Patient>> list(@ApiParam("查询条件对象") Patient patient)
    {
        startPage();
        List<Patient> list = patientService.selectPatientList(patient);
        return getDataTable(list);
    }

    /**
     * 导出患者列表
     */
    @ApiOperation("导出患者列表")
    @PreAuthorize("@ss.hasPermi('hospital:patient:export')")
    @Log(title = "患者", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, Patient patient)
    {
        List<Patient> list = patientService.selectPatientList(patient);
        ExcelUtil<Patient> util = new ExcelUtil<Patient>(Patient.class);
        util.exportExcel(response, list, "患者数据");
    }

    /**
     * 获取患者详细信息
     */
    @ApiOperation("获取患者详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:patient:query')")
    @GetMapping(value = "/{id}")
    public R<Patient> getInfo(@PathVariable("id") @ApiParam("患者ID") Long id)
    {
        return R.ok(patientService.selectPatientById(id));
    }

    /**
     * 新增患者
     */
    @ApiOperation("新增患者")
    @PreAuthorize("@ss.hasPermi('hospital:patient:add')")
    @Log(title = "患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的患者对象") Patient patient)
    {
        return toAjax(patientService.insertPatient(patient));
    }

    /**
     * 修改患者
     */
    @ApiOperation("修改患者")
    @PreAuthorize("@ss.hasPermi('hospital:patient:edit')")
    @Log(title = "患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的患者对象") Patient patient)
    {
        return toAjax(patientService.updatePatient(patient));
    }

    /**
     * 删除患者
     */
    @ApiOperation("删除患者")
    @PreAuthorize("@ss.hasPermi('hospital:patient:remove')")
    @Log(title = "患者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的患者ID") Long[] ids)
    {
        return toAjax(patientService.deletePatientByIds(ids));
    }
}
