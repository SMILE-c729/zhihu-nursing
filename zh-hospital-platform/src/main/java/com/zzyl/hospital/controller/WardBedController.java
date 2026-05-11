package com.zzyl.hospital.controller;

import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.WardBed;
import com.zzyl.hospital.service.IWardBedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 病床Controller
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@RestController
@RequestMapping("/patient/wardBed")
@Api(tags = "病床管理")
public class WardBedController extends BaseController
{
    @Autowired
    private IWardBedService wardBedService;

    /**
     * 查询病床列表
     */
    
    @PreAuthorize("@ss.hasPermi('patient:wardBed:list')")
    @GetMapping("/list")
    @ApiOperation("查询病床列表")
    public TableDataInfo list(WardBed wardBed)
    {
        startPage();
        List<WardBed> list = wardBedService.selectWardBedList(wardBed);
        return getDataTable(list);
    }

    /**
     * 获取病床详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:wardBed:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取病床详细信息")
    public R<WardBed> getInfo(@ApiParam(value = "病床ID", required = true)  @PathVariable("id") Long id)
    {
        return R.ok(wardBedService.selectWardBedById(id));
    }

    /**
     * 新增病床
     */
    @PreAuthorize("@ss.hasPermi('patient:wardBed:add')")
    @Log(title = "病床", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增病床")
    public AjaxResult add(@RequestBody WardBed wardBed)
    {
        return toAjax(wardBedService.insertWardBed(wardBed));
    }

    /**
     * 修改病床
     */
    @ApiOperation("修改病床")
    @PreAuthorize("@ss.hasPermi('patient:wardBed:edit')")
    @Log(title = "病床", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WardBed wardBed)
    {
        return toAjax(wardBedService.updateWardBed(wardBed));
    }

    /**
     * 删除病床
     */
    @ApiOperation("删除病床")
    @PreAuthorize("@ss.hasPermi('patient:wardBed:remove')")
    @Log(title = "病床", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam(value = "病床ID", required = true)  @PathVariable Long[] ids)
    {
        return toAjax(wardBedService.deleteWardBedByIds(ids));
    }
}
