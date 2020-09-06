package com.cutety.bill_book;

import com.cutety.controller.CodeController;
import com.cutety.entity.User;
import com.cutety.service.UserService;
import com.cutety.utils.CodeUtil;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeTest {
    @Autowired
    private UserService userService;
    @Test
    void loginTest() {



    }

    @Test
    void registerTest() {
        System.out.println(userService.ifUsernameExist("ninja"));
    }
}
