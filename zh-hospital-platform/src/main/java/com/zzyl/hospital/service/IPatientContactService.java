package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.PatientContact;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.dto.UserLoginRequestDto;
import com.zzyl.hospital.vo.LoginVo;

/**
 * memberService接口
 * 
 * @author alexis
 * @date 2026-02-08
 */
public interface IPatientContactService extends IService<PatientContact>
{
    /**
     * 小程序端登录
     *
     * @param userLoginRequestDto 登录信息
     * @return 登录结果
     */
    LoginVo login(UserLoginRequestDto userLoginRequestDto);
}
