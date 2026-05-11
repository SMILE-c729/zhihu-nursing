package com.zzyl;

import com.zzyl.hospital.service.impl.WxChatLoginImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HttpClineTest {
    @Autowired
    private WxChatLoginImpl wxChatLogin;

    @Test
    public void getopenId(){
        String code = wxChatLogin.getCode("0e3STwHa1cau7L0xuFGa135A3W0STwHR");
        System.out.println(code);
    }
    @Test
    public void getPhone(){
        String phone = wxChatLogin.getPhoneCode("4e6f677ca81749f6b17e3328fdfac20b7edc2f27a79521e5ba23dc2801e1b187");
        System.out.println(phone);
    }
}
