package com.zzyl.nursing.service;

public interface wxChatLogin {
    /**
     * 获取code
     * @param code
     * @return
     */
    String getCode(String code);
    /**
     * 获取session_key和openid
     * @param phoneCode
     * @return String
     */
    String getPhoneCode(String phoneCode);
}
