package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import com.zzyl.hospital.dto.MonitoringDeviceDto;
import com.zzyl.hospital.vo.MonitoringDeviceDetailVo;
import com.zzyl.hospital.vo.ProductVo;
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
import com.zzyl.hospital.domain.MonitoringDevice;
import com.zzyl.hospital.service.IMonitoringDeviceService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 设备Controller
 *
 * @author alexis
 * @date 2026-02-11
 */
@Api(tags = "设备管理")
@RestController
@RequestMapping("/hospital/monitoringDevice")
public class MonitoringDeviceController extends BaseController {
    @Autowired
    private IMonitoringDeviceService monitoringDeviceService;

    /**
     * 查询设备列表
     */
    @ApiOperation("查询设备列表")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:list')")
    @GetMapping("/list")
    public TableDataInfo<List<MonitoringDevice>> list(@ApiParam("查询条件对象") MonitoringDevice monitoringDevice) {
        startPage();
        List<MonitoringDevice> list = monitoringDeviceService.selectMonitoringDeviceList(monitoringDevice);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @ApiOperation("导出设备列表")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:export')")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MonitoringDevice monitoringDevice) {
        List<MonitoringDevice> list = monitoringDeviceService.selectMonitoringDeviceList(monitoringDevice);
        ExcelUtil<MonitoringDevice> util = new ExcelUtil<>(MonitoringDevice.class);
        util.exportExcel(response, list, "设备数据");
    }

    /*
     * 从物联网平台同步产品列表
     * */
    @ApiOperation("从物联网平台同步产品列表")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:sync')")
    @Log(title = "设备管理", businessType = BusinessType.OTHER)
    @PostMapping("/syncProductList")
    public AjaxResult syncProductList() {
        monitoringDeviceService.syncProductList();
        return AjaxResult.success();
    }

    /**
     * 查询所有产品列表
     */
    @ApiOperation("查询所有产品列表")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:list')")
    @GetMapping("/allProduct")
    public AjaxResult allProduct() {
        return success(monitoringDeviceService.allProduct());
    }


    /*
     * 注册设备
     * */
    @PostMapping("/register")
    @ApiOperation(value = "注册设备")
    public AjaxResult registerMonitoringDevice(@RequestBody MonitoringDeviceDto monitoringDeviceDto) {
        monitoringDeviceService.registerMonitoringDevice(monitoringDeviceDto);
        return success();
    }

    /*
     * 查询设备详情
     * */
    @ApiOperation("查询设备详情")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:query')")
    @GetMapping("/{iotId}")
    public R<MonitoringDeviceDetailVo> queryMonitoringDeviceDetail(@ApiParam("设备iotId") @PathVariable("iotId") String iotId) {
        return R.ok(monitoringDeviceService.queryMonitoringDeviceDetail(iotId));
    }
    /**
     * 查询设备上报数据
     */
    @GetMapping("/queryServiceProperties/{iotId}")
    @ApiOperation("查询设备上报数据")
    public AjaxResult queryServiceProperties(@PathVariable("iotId") String iotId) {
        AjaxResult ajaxResult = monitoringDeviceService.queryServiceProperties(iotId);
        return ajaxResult;
    }

    /**
     * 查询设备属性状态，兼容前端历史接口。
     */
    @PostMapping("/queryDevicePropertyStatus")
    @ApiOperation("查询设备属性状态")
    public AjaxResult queryDevicePropertyStatus(@RequestBody MonitoringDeviceDto monitoringDeviceDto) {
        return monitoringDeviceService.queryServiceProperties(monitoringDeviceDto.getIotId());
    }

    /**
     * 修改设备
     */
    @ApiOperation("修改设备")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:edit')")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的设备对象") MonitoringDeviceDto monitoringDeviceDto) {
        return toAjax(monitoringDeviceService.updateMonitoringDevice(monitoringDeviceDto));
    }

    /**
     * 删除设备
     */
    @ApiOperation("删除设备")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:remove')")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{iotId}")
    public AjaxResult remove(@PathVariable("iotId") @ApiParam("设备iotId") String iotId) {
        return toAjax(monitoringDeviceService.deleteMonitoringDeviceByIotId(iotId));
    }

    /**
     * 查询产品详情
     */
    @ApiOperation("查询产品详情")
    @PreAuthorize("@ss.hasPermi('hospital:monitoringDevice:query')")
    @GetMapping("/queryProduct/{productKey}")
    public AjaxResult queryProduct(@PathVariable("productKey") @ApiParam("产品id") String productKey) {
        return monitoringDeviceService.queryProduct(productKey);
    }
}
