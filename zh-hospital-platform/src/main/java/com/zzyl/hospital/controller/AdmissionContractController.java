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
import com.zzyl.hospital.domain.AdmissionContract;
import com.zzyl.hospital.service.IAdmissionContractService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 合同Controller
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Api(tags = "入院合同管理")
@RestController
@RequestMapping("/hospital/admissionContract")
public class AdmissionContractController extends BaseController
{
    @Autowired
    private IAdmissionContractService admissionContractService;

    /**
     * 查询合同列表
     */
    @ApiOperation("查询合同列表")
    @PreAuthorize("@ss.hasPermi('hospital:admissionContract:list')")
    @GetMapping("/list")
    public TableDataInfo<List<AdmissionContract>> list(@ApiParam("查询条件对象") AdmissionContract admissionContract)
    {
        startPage();
        List<AdmissionContract> list = admissionContractService.selectAdmissionContractList(admissionContract);
        return getDataTable(list);
    }

    /**
     * 导出合同列表
     */
    @ApiOperation("导出合同列表")
    @PreAuthorize("@ss.hasPermi('hospital:admissionContract:export')")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, AdmissionContract admissionContract)
    {
        List<AdmissionContract> list = admissionContractService.selectAdmissionContractList(admissionContract);
        ExcelUtil<AdmissionContract> util = new ExcelUtil<AdmissionContract>(AdmissionContract.class);
        util.exportExcel(response, list, "合同数据");
    }

    /**
     * 获取合同详细信息
     */
    @ApiOperation("获取合同详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:admissionContract:query')")
    @GetMapping(value = "/{id}")
    public R<AdmissionContract> getInfo(@PathVariable("id") @ApiParam("合同ID") Long id)
    {
        return R.ok(admissionContractService.selectAdmissionContractById(id));
    }

    /**
     * 新增合同
     */
    @ApiOperation("新增合同")
    @PreAuthorize("@ss.hasPermi('hospital:admissionContract:add')")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的合同对象") AdmissionContract admissionContract)
    {
        return toAjax(admissionContractService.insertAdmissionContract(admissionContract));
    }

    /**
     * 修改合同
     */
    @ApiOperation("修改合同")
    @PreAuthorize("@ss.hasPermi('hospital:admissionContract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的合同对象") AdmissionContract admissionContract)
    {
        return toAjax(admissionContractService.updateAdmissionContract(admissionContract));
    }

    /**
     * 删除合同
     */
    @ApiOperation("删除合同")
    @PreAuthorize("@ss.hasPermi('hospital:admissionContract:remove')")
    @Log(title = "合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的合同ID") Long[] ids)
    {
        return toAjax(admissionContractService.deleteAdmissionContractByIds(ids));
    }
}
