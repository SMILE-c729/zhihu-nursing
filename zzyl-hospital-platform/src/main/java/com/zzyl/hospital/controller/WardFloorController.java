package com.zzyl.hospital.controller;

import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.WardFloor;
import com.zzyl.hospital.service.IWardFloorService;
import com.zzyl.hospital.vo.WardStructureTreeVo;
import com.zzyl.hospital.vo.WardFloorVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 楼层Controller
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@RestController
@RequestMapping("/patient/wardFloor")
@Api(tags = "楼层管理")
public class WardFloorController extends BaseController
{
    @Autowired
    private IWardFloorService wardFloorService;

    /**
     * 查询楼层列表
     */
    @PreAuthorize("@ss.hasPermi('patient:wardFloor:list')")
    @GetMapping("/list")
    @ApiOperation("查询所有楼层列表")
    public R<List<WardFloor>> list()
    {
        List<WardFloor> list = wardFloorService.list();
        return R.ok(list);
    }

    /**
     * 获取楼层详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:wardFloor:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取楼层详细信息")
    public R<WardFloor> getInfo(@ApiParam(value = "楼层ID", required = true) @PathVariable("id") Long id)
    {
        return R.ok(wardFloorService.selectWardFloorById(id));
    }

    /**
     * 新增楼层
     */
    @PreAuthorize("@ss.hasPermi('patient:wardFloor:add')")
    @Log(title = "楼层", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增楼层")
    public AjaxResult add(@ApiParam(value = "楼层信息", required = true)  @RequestBody WardFloor wardFloor)
    {
        return toAjax(wardFloorService.insertWardFloor(wardFloor));
    }

    /**
     * 修改楼层
     */
    @ApiOperation("修改楼层")
    @PreAuthorize("@ss.hasPermi('patient:wardFloor:edit')")
    @Log(title = "楼层", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@ApiParam(value = "楼层信息", required = true) @RequestBody WardFloor wardFloor)
    {
        return toAjax(wardFloorService.updateWardFloor(wardFloor));
    }

    /**
     * 删除楼层
     */
    @ApiOperation("删除楼层")
    @PreAuthorize("@ss.hasPermi('patient:wardFloor:remove')")
    @Log(title = "楼层", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam(value = "楼层ID", required = true) @PathVariable Long[] ids)
    {
        return toAjax(wardFloorService.deleteWardFloorByIds(ids));
    }

    @GetMapping("/getAllWardFloorsWithNur")
    @ApiOperation(value = "获取所有楼层 (责任患者)", notes = "无需参数，获取所有楼层，返回楼层信息列表")
    public R<List<WardFloor>> getAllWardFloorsWithNur() {
        List<WardFloor> list = wardFloorService.selectAllByNur();
        return R.ok(list);
    }

    /**
     * 获取所有有智能设备的楼层
     */
    @GetMapping("/getAllWardFloorsWithMonitoringDevice")
    @ApiOperation(value = "获取所有有智能设备的楼层", notes = "无需参数，返回存在智能设备的楼层列表")
    public R<List<WardFloorVo>> getAllWardFloorsWithMonitoringDevice() {
        List<WardFloorVo> list = wardFloorService.selectAllByMonitoringDevice();
        return R.ok(list);
    }

    /*
    * 1.3 根据病床状态查询获取所有楼层数据
    * */
    @GetMapping("/getWardRoomAndWardBedByWardBedStatus/{status}")
    @ApiOperation("根据病床状态查询获取所有楼层数据")
    public R<List<WardStructureTreeVo>> getWardRoomAndWardBedByWardBedStatus(
            @ApiParam(value = "wardBed status", required = true) @PathVariable Integer status) {
        return R.ok(wardFloorService.getWardRoomAndWardBedByWardBedStatus(status));
    }
}
