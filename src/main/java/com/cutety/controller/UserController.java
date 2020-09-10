package com.cutety.controller;

import com.alibaba.fastjson.JSON;
import com.cutety.entity.LoginDTO;
import com.cutety.entity.Response;
import com.cutety.entity.User;
import com.cutety.service.UserService;
import com.cutety.utils.CodeUtil;
import com.cutety.utils.JWTUtil;
import com.cutety.utils.RedisUtil;
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
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.http.HttpAuthenticator;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RedisUtil redisUtil;
    public static Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(RedisUtil redisUtil, UserService userService) {
        this.redisUtil = redisUtil;
        this.userService = userService;
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/register")
    public int addUser(User user) {
        String password = user.getPassword();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String pwdAfterHash = new SimpleHash("md5", password, salt, 2).toString();
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
    @ResponseBody
    @PostMapping("/login")
    public Response login(@RequestBody LoginDTO user) {
        Response response = new Response();
        String captcha = redisUtil.get(user.getCaptchaKey());
        log.info("captchaKey:{}",captcha);
        if(captcha == null) {
            response.setMsg("验证码已过期");
            response.setStatus("4013");
            return response;
        }
        if (!user.getCaptcha().equalsIgnoreCase(captcha)) {
            response.setMsg("验证码错误");
            response.setStatus("4014");
            return response;
        }
        //删除验证码
        redisUtil.delete(user.getCaptchaKey() );
        String username = user.getUsername();
        String password = user.getPassword();
        log.info("username:{},password:{}", username, password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            User loginUser = userService.getByUsername(username);
            HashMap<String, String> payload = new HashMap<>();
            payload.put("userId",loginUser.getId().toString());
            payload.put("username",usernamePasswordToken.getUsername());
            String token = JWTUtil.createToken(payload, 5);
            redisUtil.setEx(username,token,5,TimeUnit.DAYS);
            response.setStatus("200");
            response.setMsg("登陆成功");
            response.setToken(token);
            response.setObject(JSON.toJSONString(payload));
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

    @PostMapping("/logout")
    @ResponseBody
    public String logout(String username) {
        redisUtil.delete(username);
        log.info("{} logout successfully.",username);
        return "successfully logout";
    }

    @ResponseBody
    @GetMapping("/show")
    public String show() {
        return "show me ... your tits.";
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/checkUsernameValidity")
    public boolean checkUsernameValidity(String username) {
        return userService.ifUsernameExist(username) <= 0;
    }
}
