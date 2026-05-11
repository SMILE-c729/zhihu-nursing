package com.zzyl.hospital.controller;

import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.WardRoom;
import com.zzyl.hospital.service.IWardRoomService;
import com.zzyl.hospital.vo.WardRoomVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房间Controller
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@RestController
@RequestMapping("/patient/wardRoom")
@Api(tags = "房间管理")
public class WardRoomController extends BaseController
{
    @Autowired
    private IWardRoomService wardRoomService;

    @GetMapping("/getWardRoomsWithNurByWardFloorId/{wardFloorId}")
    @ApiOperation("获取所有房间（责任患者）")
    public R<List<WardRoomVo>> getWardRoomsWithNurByWardFloorId(@PathVariable Long wardFloorId) {
        List<WardRoomVo> list = wardRoomService.getWardRoomsWithNurByWardFloorId(wardFloorId);
        return R.ok(list);
    }

    /**
     * 根据楼层ID获取房间中的智能设备及数据
     */
    @GetMapping("/getWardRoomsWithMonitoringDeviceByWardFloorId/{wardFloorId}")
    @ApiOperation("根据楼层ID获取房间中的智能设备及数据")
    public R<List<WardRoomVo>> getWardRoomsWithMonitoringDeviceByWardFloorId(
            @ApiParam(value = "楼层ID", required = true) @PathVariable Long wardFloorId) {
        List<WardRoomVo> list = wardRoomService.getWardRoomsWithMonitoringDeviceByWardFloorId(wardFloorId);
        return R.ok(list);
    }

    @GetMapping("/getWardRoomsByWardFloorId/{wardFloorId}")
    @ApiOperation("获取所有房间（入院配置）")
    public R<List<WardRoomVo>> getWardRoomsByWardFloorId(@ApiParam(value = "楼层ID", required = true)  @PathVariable Long wardFloorId) {
        List<WardRoomVo> list = wardRoomService.getWardRoomsByWardFloorId(wardFloorId);
        return R.ok(list);
    }
    /**
     * 查询房间列表
     */
    @PreAuthorize("@ss.hasPermi('patient:wardRoom:list')")
    @GetMapping("/list")
    @ApiOperation("查询房间列表")
    public TableDataInfo list(@ApiParam(value = "房间信息", required = true)  WardRoom wardRoom)
    {
        startPage();
        List<WardRoom> list = wardRoomService.selectWardRoomList(wardRoom);
        return getDataTable(list);
    }

    /**
     * 获取房间详细信息
     */
    @PreAuthorize("@ss.hasPermi('patient:wardRoom:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取房间详细信息")
    public R<WardRoom> getInfo(@ApiParam(value = "房间ID", required = true)  @PathVariable("id") Long id)
    {
        return R.ok(wardRoomService.selectWardRoomById(id));
    }

    /**
     * 新增房间
     */
    @PreAuthorize("@ss.hasPermi('patient:wardRoom:add')")
    @Log(title = "房间", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增房间")
    public AjaxResult add(@ApiParam(value = "房间信息", required = true)  @RequestBody WardRoom wardRoom)
    {
        return toAjax(wardRoomService.insertWardRoom(wardRoom));
    }

    /**
     * 修改房间
     */
    @ApiOperation("修改房间")
    @PreAuthorize("@ss.hasPermi('patient:wardRoom:edit')")
    @Log(title = "房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@ApiParam(value = "房间信息", required = true)  @RequestBody WardRoom wardRoom)
    {
        return toAjax(wardRoomService.updateWardRoom(wardRoom));
    }

    /**
     * 删除房间
     */
    @ApiOperation("删除房间")
    @PreAuthorize("@ss.hasPermi('patient:wardRoom:remove')")
    @Log(title = "房间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@ApiParam(value = "房间ID数组", required = true)  @PathVariable Long[] ids)
    {
        return toAjax(wardRoomService.deleteWardRoomByIds(ids));
    }
    /*
     * 根据房间id查询房间数据
     * */
    @GetMapping("/one/{id}")
    @ApiOperation("按照房间id查询楼层、房间、价格")
    public R<WardRoomVo> getWardRoomById(@ApiParam(value = "房间ID", required = true) @PathVariable("id") Long id){
        WardRoomVo wardRoomVo = wardRoomService.getWardRoomById(id);
        return R.ok(wardRoomVo);
    }
}
