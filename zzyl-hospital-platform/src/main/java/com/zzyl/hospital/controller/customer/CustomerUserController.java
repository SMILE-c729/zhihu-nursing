package com.zzyl.hospital.controller.customer;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.hospital.dto.MonitoringDeviceStatisticsQueryDto;
import com.zzyl.hospital.service.ICustomerUserDataService;
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
@Api(tags = "客户端-健康数据")
public class CustomerUserController extends BaseController {

    @Autowired
    private ICustomerUserDataService customerUserDataService;

    /**
     * 按天统计查询指标数据
     */
    @GetMapping("/queryVitalSignDataListByDay")
    @ApiOperation("按天统计查询指标数据")
    public AjaxResult queryVitalSignDataListByDay(@ApiParam("查询参数") MonitoringDeviceStatisticsQueryDto queryDto) {
        return success(customerUserDataService.queryVitalSignDataListByDay(queryDto))
                .put("operationTime", null);
    }

    /**
     * 按周统计查询指标数据
     */
    @GetMapping("/pageQueryWarningData")
    @ApiOperation("按周统计查询指标数据")
    public AjaxResult pageQueryWarningData(@ApiParam("查询参数") MonitoringDeviceStatisticsQueryDto queryDto) {
        return success(customerUserDataService.pageQueryWarningData(queryDto))
                .put("operationTime", null);
    }
}
