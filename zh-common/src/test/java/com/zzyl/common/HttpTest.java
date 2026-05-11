package com.zzyl.common;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class HttpTest {
    /*
    * 带参数请求get
    * */
    @Test
    public void getHttpClient() {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", 1);
        params.put("pageSize", 10);
        HttpResponse authorization = HttpUtil.createRequest(Method.GET, "http://localhost:8080/nursing/project/list")
                .form(params)
                .header("authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyeSIsImxvZ2luX3VzZXJfa2V5IjoiNmYyYzQzZDEtMGUxYS00MmY2LThhMDctMTZiZTA3OGQ2YTQ4In0.z3Q9OYivtzWrIyni-CuRFfCJ5CRd4qc3hqEl7TK2jhpXFcGBSl67uxiJO15cySLQrp3JI5UveMoBTQArTOwcpA ")
                .execute();
        if (authorization.isOk()) {
            System.out.println(authorization.body());
        }
    }
    /*
    * 带参数请求post
    * */
    @Test
    public void postHttpClient() {
        String url = "http://localhost:8080/nursing/project";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "护理项目测试");
        paramMap.put("orderNo", 1);
        paramMap.put("unit", "次");
        paramMap.put("price", 10.00);
        paramMap.put("image", "https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/ae7cf766-fb7b-49ff-a73c-c86c25f280e1.png");
        paramMap.put("nursingRequirement", "无特殊要求");
        paramMap.put("status", 1);
        HttpResponse authorization = HttpUtil.createRequest(Method.POST, url)
                .header("authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyeSIsImxvZ2luX3VzZXJfa2V5IjoiNmYyYzQzZDEtMGUxYS00MmY2LThhMDctMTZiZTA3OGQ2YTQ4In0.z3Q9OYivtzWrIyni-CuRFfCJ5CRd4qc3hqEl7TK2jhpXFcGBSl67uxiJO15cySLQrp3JI5UveMoBTQArTOwcpA ")
                .body(JSONUtil.toJsonStr(paramMap))
                .execute();
        if (authorization.isOk()) {
         System.out.println(authorization.body());
     }
    }
}
