package com.cutety.controller;

import com.cutety.entity.Response;
import com.cutety.entity.User;
import com.cutety.service.UserService;
import com.cutety.utils.CodeUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    public static Logger log = LoggerFactory.getLogger(UserController.class);
    @CrossOrigin
    @ResponseBody
    @PostMapping("/register")
    public int addUser(User user) {
        String password = user.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String pwdAfterHash = new SimpleHash("md5",password,salt,2).toString();
        user.setPassword(pwdAfterHash);
        user.setSalt(salt);
        return userService.addUser(user);
    }
    @ResponseBody
    @PostMapping("/signIn")
    public String hello(HttpServletRequest request) {
        log.info("进入验证码判断");
        if (!CodeUtil.checkVerifyCode(request)) {
            return "验证码有误！";
        } else {
            return "登陆成功";
        }
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping("/login")
    public Response login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        log.info("username:{},password:{}",username,password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Response response = new Response();
        try {
            subject.login(token);
            response.setStatus("200");
            response.setMsg("登陆成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            response.setStatus("4012");
            response.setMsg("账号有误");

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            response.setStatus("401");
            response.setMsg("密码错误");
        }
        return response;
    }

    @ResponseBody
    @GetMapping("/show")
    public String show() {
        return "show me ...";
    }
    @CrossOrigin
    @ResponseBody
    @PostMapping("/checkUsernameValidity")
    public boolean checkUsernameValidity(String username) {
        return userService.ifUsernameExist(username) <= 0;
    }
}
