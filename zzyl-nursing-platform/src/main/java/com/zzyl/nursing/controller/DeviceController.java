package com.zzyl.nursing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import com.zzyl.nursing.dto.DeviceDto;
import com.zzyl.nursing.vo.DeviceDetailVo;
import com.zzyl.nursing.vo.ProductVo;
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
import com.zzyl.nursing.domain.Device;
import com.zzyl.nursing.service.IDeviceService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 设备Controller
 *
 * @author alexis
 * @date 2026-02-11
 */
@Api("设备管理")
@RestController
@RequestMapping("/nursing/device")
public class DeviceController extends BaseController {
    @Autowired
    private IDeviceService deviceService;

    /**
     * 查询设备列表
     */
    @ApiOperation("查询设备列表")
    @PreAuthorize("@ss.hasPermi('nursing:device:list')")
    @GetMapping("/list")
    public TableDataInfo<List<Device>> list(@ApiParam("查询条件对象") Device device) {
        startPage();
        List<Device> list = deviceService.selectDeviceList(device);
        return getDataTable(list);
    }

    /*
     * 从物联网平台同步产品列表
     * */
    @ApiOperation("从物联网平台同步产品列表")
    @PreAuthorize("@ss.hasPermi('nursing:device:sync')")
    @Log(title = "设备管理", businessType = BusinessType.OTHER)
    @PostMapping("/syncProductList")
    public AjaxResult syncProductList() {
        deviceService.syncProductList();
        return AjaxResult.success();
    }

    /**
     * 查询所有产品列表
     */
    @ApiOperation("查询所有产品列表")
    @PreAuthorize("@ss.hasPermi('nursing:device:list')")
    @GetMapping("/allProduct")
    public AjaxResult allProduct() {
        return success(deviceService.allProduct());
    }


    /*
     * 注册设备
     * */
    @PostMapping("/register")
    @ApiOperation(value = "注册设备")
    public AjaxResult registerDevice(@RequestBody DeviceDto deviceDto) {
        deviceService.registerDevice(deviceDto);
        return success();
    }

    /*
     * 查询设备详情
     * */
    @ApiOperation("查询设备详情")
    @PreAuthorize("@ss.hasPermi('nursing:device:query')")
    @GetMapping("/{iotId}")
    public R<DeviceDetailVo> queryDeviceDetail(@ApiParam("设备iotId") @PathVariable("iotId") String iotId) {
        return R.ok(deviceService.queryDeviceDetail(iotId));
    }
    /**
     * 查询设备上报数据
     */
    @GetMapping("/queryServiceProperties/{iotId}")
    @ApiOperation("查询设备上报数据")
    public AjaxResult queryServiceProperties(@PathVariable("iotId") String iotId) {
        AjaxResult ajaxResult = deviceService.queryServiceProperties(iotId);
        return ajaxResult;
    }

    /**
     * 修改设备
     */
    @ApiOperation("修改设备")
    @PreAuthorize("@ss.hasPermi('nursing:device:edit')")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的设备对象") DeviceDto deviceDto) {
        return toAjax(deviceService.updateDevice(deviceDto));
    }

    /**
     * 删除设备
     */
    @ApiOperation("删除设备")
    @PreAuthorize("@ss.hasPermi('nursing:device:remove')")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{iotId}")
    public AjaxResult remove(@PathVariable("iotId") @ApiParam("设备iotId") String iotId) {
        return toAjax(deviceService.deleteDeviceByIotId(iotId));
    }

    /**
     * 查询产品详情
     */
    @ApiOperation("查询产品详情")
    @PreAuthorize("@ss.hasPermi('nursing:device:query')")
    @GetMapping("/queryProduct/{productKey}")
    public AjaxResult queryProduct(@PathVariable("productKey") @ApiParam("产品id") String productKey) {
        return deviceService.queryProduct(productKey);
    }
}
