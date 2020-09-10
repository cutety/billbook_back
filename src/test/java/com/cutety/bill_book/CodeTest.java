package com.cutety.bill_book;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cutety.controller.CodeController;
import com.cutety.entity.User;
import com.cutety.service.UserService;
import com.cutety.utils.CodeUtil;
import com.cutety.utils.JWTUtil;
import com.cutety.utils.RedisUtil;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class CodeTest {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Test

    void loginTest() {
        String s = redisUtil.get("captcha:verification:c290aa1f-981f-44d6-8017-113f62532554");
        System.out.println(s);
    }

    @Test
    void registerTest() {
        System.out.println(userService.ifUsernameExist("ninja"));
    }

    @Test
    void testToken() {
        HashMap<String, String> payload = new HashMap<>();
        payload.put("userId","1");
        payload.put("username","alex");
        String token = JWTUtil.createToken(payload, 20);
        DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
    }
}
