package com.cutety.bill_book;

import com.cutety.controller.CodeController;
import com.cutety.entity.User;
import com.cutety.service.UserService;
import com.cutety.utils.CodeUtil;
import com.cutety.utils.RedisUtil;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
