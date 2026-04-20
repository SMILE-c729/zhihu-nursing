package com.zzyl.nursing.controller.member;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.nursing.dto.UserLoginRequestDto;
import com.zzyl.nursing.service.IDeviceService;
import com.zzyl.nursing.service.IFamilyMemberService;
import com.zzyl.nursing.vo.LoginVo;
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
@Api("Member Management")
@RestController
@RequestMapping("/member/user")
public class FamilyMemberController extends BaseController {
    @Autowired
    private IFamilyMemberService familyMemberService;
    @Autowired
    private IDeviceService deviceService;

    /**
     * 小程序登录
     */
    @PostMapping("/login")
    @ApiOperation("小程序登录")
    public AjaxResult login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        LoginVo loginVo = familyMemberService.login(userLoginRequestDto);
        return success(loginVo);
    }

    /**
     * 查询健康数据（设备上报属性）
     */
    @GetMapping("/queryServiceProperties/{iotId}")
    @ApiOperation("查询健康数据")
    public AjaxResult queryServiceProperties(@PathVariable("iotId") @ApiParam("设备id") String iotId) {
        return deviceService.queryServiceProperties(iotId);
    }
}
