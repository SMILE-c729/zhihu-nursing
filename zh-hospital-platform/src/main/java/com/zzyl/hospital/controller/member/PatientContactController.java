package com.zzyl.hospital.controller.member;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.hospital.dto.UserLoginRequestDto;
import com.zzyl.hospital.service.IMonitoringDeviceService;
import com.zzyl.hospital.service.IPatientContactService;
import com.zzyl.hospital.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Family member controller
 */
@Api(tags = "患者联系人管理")
@RestController
@RequestMapping("/member/user")
public class PatientContactController extends BaseController {
    @Autowired
    private IPatientContactService patientContactService;
    @Autowired
    private IMonitoringDeviceService monitoringDeviceService;

    /**
     * 小程序登录
     */
    @PostMapping("/login")
    @ApiOperation("小程序登录")
    public AjaxResult login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        LoginVo loginVo = patientContactService.login(userLoginRequestDto);
        return success(loginVo);
    }

    /**
     * 查询健康数据（设备上报属性）
     */
    @GetMapping("/queryServiceProperties/{iotId}")
    @ApiOperation("查询健康数据")
    public AjaxResult queryServiceProperties(@PathVariable("iotId") @ApiParam("设备id") String iotId) {
        return monitoringDeviceService.queryServiceProperties(iotId);
    }
}
