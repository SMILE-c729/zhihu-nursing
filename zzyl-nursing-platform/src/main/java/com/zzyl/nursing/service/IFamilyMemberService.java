package com.zzyl.nursing.service;

import java.util.List;
import com.zzyl.nursing.domain.FamilyMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.nursing.dto.UserLoginRequestDto;
import com.zzyl.nursing.vo.LoginVo;

/**
 * memberService接口
 * 
 * @author alexis
 * @date 2026-02-08
 */
public interface IFamilyMemberService extends IService<FamilyMember>
{
    /**
     * 小程序端登录
     *
     * @param userLoginRequestDto 登录信息
     * @return 登录结果
     */
    LoginVo login(UserLoginRequestDto userLoginRequestDto);
}
