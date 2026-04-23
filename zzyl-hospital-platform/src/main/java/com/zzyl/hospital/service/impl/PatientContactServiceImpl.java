package com.zzyl.hospital.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.framework.web.service.TokenService;
import com.zzyl.hospital.service.impl.WxChatLoginImpl;
import com.zzyl.hospital.domain.PatientContact;
import com.zzyl.hospital.dto.UserLoginRequestDto;
import com.zzyl.hospital.mapper.PatientContactMapper;
import com.zzyl.hospital.service.IPatientContactService;
import com.zzyl.hospital.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 家庭成员服务实现
 */
@Slf4j
@Service
public class PatientContactServiceImpl extends ServiceImpl<PatientContactMapper, PatientContact> implements IPatientContactService {

    @Autowired
    private WxChatLoginImpl wechatService;

    @Autowired
    private TokenService tokenService;

    static List<String> DEFAULT_NICKNAME_PREFIX = ListUtil.of(
            "生活更美好",
            "大桔大利",
            "日富一日",
            "好柿开花",
            "柿柿如意",
            "一椰暴富",
            "大柚所为",
            "杨梅吐气",
            "天生荔枝"
    );

    /**
     * 小程序端登录
     * @param userLoginRequestDto
     * @return
     */
    @Override
    public LoginVo login(UserLoginRequestDto userLoginRequestDto) {
        // 1.调用微信api,根据code获取openId
        String openId = wechatService.getCode(userLoginRequestDto.getCode());

        // 2.根据openId查询用户
        PatientContact patientContact = getOne(Wrappers.<PatientContact>lambdaQuery(PatientContact.class)
                .eq(PatientContact::getOpenId, openId));

        // 3.如果用户为空，则将openId设置到对象中
        if (ObjectUtil.isEmpty(patientContact)) {
            patientContact = PatientContact.builder().openId(openId).build();
        }

        // 4.调用微信api获取用户绑定的手机号
        String phone = wechatService.getPhoneCode(userLoginRequestDto.getPhoneCode());

        // 5.保存或修改用户
        saveOrUpdatePatientContact(patientContact, phone);

        // 6.将用户id存入token,返回
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", patientContact.getId());
        claims.put("nickName", patientContact.getName());

        String token = tokenService.createToken(claims);
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setNickName(patientContact.getName());
        return loginVo;
    }

    /**
     * 保存或修改客户
     * @param member
     * @param phone
     */
    private void saveOrUpdatePatientContact(PatientContact member, String phone) {

        // 1.判断取到的手机号与数据库中保存的手机号不一样
        if(ObjectUtil.notEqual(phone, member.getPhone())) {
            // 修改手机号
            member.setPhone(phone);
        }
        // 2.判断id存在，则修改
        if (ObjectUtil.isNotEmpty(member.getId())) {
            updateById(member);
            return;
        }
        // 3.id不存在，保存新用户
        // 随机组装昵称，词组+手机号后四位
        String nickName = DEFAULT_NICKNAME_PREFIX.get((int) (Math.random() * DEFAULT_NICKNAME_PREFIX.size()))
                + StringUtils.substring(member.getPhone(), 7);

        member.setName(nickName);
        save(member);
    }
}
