package com.zzyl.nursing.controller.customer;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.nursing.dto.DeviceStatisticsQueryDto;
import com.zzyl.nursing.service.ICustomerUserDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端用户健康数据接口
 */
@RestController
@RequestMapping("/customer/user")
@Api(tags = "客户端用户健康数据接口")
public class CustomerUserController extends BaseController {

    @Autowired
    private ICustomerUserDataService customerUserDataService;

    /**
     * 按天统计查询指标数据
     */
    @GetMapping("/queryDeviceDataListByDay")
    @ApiOperation("按天统计查询指标数据")
    public AjaxResult queryDeviceDataListByDay(@ApiParam("查询参数") DeviceStatisticsQueryDto queryDto) {
        return success(customerUserDataService.queryDeviceDataListByDay(queryDto))
                .put("operationTime", null);
    }

    /**
     * 按周统计查询指标数据
     */
    @GetMapping("/pageQueryAlertData")
    @ApiOperation("按周统计查询指标数据")
    public AjaxResult pageQueryAlertData(@ApiParam("查询参数") DeviceStatisticsQueryDto queryDto) {
        return success(customerUserDataService.pageQueryAlertData(queryDto))
                .put("operationTime", null);
    }
}
