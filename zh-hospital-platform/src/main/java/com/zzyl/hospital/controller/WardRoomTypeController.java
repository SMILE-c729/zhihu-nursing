package com.zzyl.hospital.controller;

import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.hospital.domain.WardRoomType;
import com.zzyl.hospital.service.IWardRoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 病房类型Controller
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@RestController
@RequestMapping("/patient/wardRoomType")
@Api(tags = "病房类型管理")
public class WardRoomTypeController extends BaseController
{
    @Autowired
    private IWardRoomTypeService wardRoomTypeService;

    /**
     * 查询病房类型列表
     */
    @GetMapping("/list")
    @ApiOperation("查询病房类型列表")
    public TableDataInfo list(WardRoomType wardRoomType)
    {
        startPage();
        List<WardRoomType> list = wardRoomTypeService.selectWardRoomTypeList(wardRoomType);
        return getDataTable(list);
    }

    /**
     * 查询病房类型列表
     */
    @GetMapping("/listAll")
    @ApiOperation("查询所有病房类型列表")
    public AjaxResult list()
    {
        List<WardRoomType> list = wardRoomTypeService.list();
        return success(list);
    }

    /**
     * 导出病房类型列表
     */
    @Log(title = "病房类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WardRoomType wardRoomType)
    {
        List<WardRoomType> list = wardRoomTypeService.selectWardRoomTypeList(wardRoomType);
        ExcelUtil<WardRoomType> util = new ExcelUtil<WardRoomType>(WardRoomType.class);
        util.exportExcel(response, list, "病房类型数据");
    }

    /**
     * 获取病房类型详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation("获取病房类型详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wardRoomTypeService.selectWardRoomTypeById(id));
    }

    /**
     * 新增病房类型
     */
    @Log(title = "病房类型", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增病房类型")
    public AjaxResult add(@RequestBody WardRoomType wardRoomType)
    {
        return toAjax(wardRoomTypeService.insertWardRoomType(wardRoomType));
    }

    /**
     * 修改病房类型
     */
    @ApiOperation("修改病房类型")
    @Log(title = "病房类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WardRoomType wardRoomType)
    {
        return toAjax(wardRoomTypeService.updateWardRoomType(wardRoomType));
    }

    /**
     * 删除病房类型
     */
    @ApiOperation("删除病房类型")
    @Log(title = "病房类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wardRoomTypeService.deleteWardRoomTypeByIds(ids));
    }
}
