package com.zzyl.nursing.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.nursing.service.wxChatLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WxChatLoginImpl implements wxChatLogin {
    // 登录
    private static final String REQUEST_URL = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code";

    // 获取token
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    // 获取手机号
    private static final String PHONE_REQUEST_URL = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=";

    // appid
    @Value("${wx.appid}")
    private String appId;
    // appsecret
    @Value("${wx.secret}")
    private String secret;

    /**
     * 获取openid
     *
     * @param code
     * @return
     */
    @Override
    public String getCode(String code) {
        //1.准备请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("js_code", code);
        //2.发送请求
        String result = HttpUtil.createRequest(Method.GET, REQUEST_URL)
                .form(params)
                .execute()
                .body();
        //3.处理结果
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 判断接口响应是否出错
        if (ObjectUtil.isNotEmpty(jsonObject.getInt("errcode"))) {
            throw new RuntimeException(jsonObject.getStr("errmsg"));
        }
        return jsonObject.getStr("openid");
    }

    /**
     * 获取手机号
     *
     * @param phoneCode
     * @return
     */
    @Override
    public String getPhoneCode(String phoneCode) {
        //1.准备请求参数access_token
        String accessToken = getAccessToken();
        //   System.out.println("accessToken:" + accessToken);
        //2.准备url以及参数
        String url = PHONE_REQUEST_URL + accessToken;
        Map<String, Object> params = new HashMap<>();
        params.put("code", phoneCode);
        //3.发送请求
        String body = HttpUtil.createRequest(Method.POST, url)
                .body(JSONUtil.toJsonStr(params))
                .execute()
                .body();
        //4.处理结果
        JSONObject jsonObject = JSONUtil.parseObj(body);

        if (jsonObject.getInt("errcode") != 0) {
            throw new RuntimeException(jsonObject.getStr("errmsg"));
        }
        JSONObject phoneInfo = jsonObject.getJSONObject("phone_info");
        return phoneInfo.getStr("phoneNumber");
    }

    /**
     * 获取access_token
     *
     * @return
     */
    private String getAccessToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", secret);
        String result = HttpUtil.createRequest(Method.GET, TOKEN_URL)
                .form(params)
                .execute()
                .body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (ObjectUtil.isNotEmpty(jsonObject.getInt("errcode"))) {
            throw new RuntimeException(jsonObject.getStr("errmsg"));
        }
        return jsonObject.getStr("access_token");
    }
}
