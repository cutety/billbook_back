package com.cutety.service.impl;
import com.cutety.controller.UserController;
import com.cutety.entity.CaptchaVO;
import com.cutety.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaService {
    public static Logger log = LoggerFactory.getLogger(CaptchaService.class);
    @Value("${server.session.timeout:300}")
    private Long timeout;


    private RedisUtil redisUtil;
    public RedisUtil getRedisUtil() {
        return redisUtil;
    }
    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
    //private final String CAPTCHA_KEY = "captcha:verification:";

    public CaptchaVO cacheCaptcha(String captcha){
        //生成一个随机标识符
        String captchaKey = UUID.randomUUID().toString();
        log.info("captchaKey is :{}",captchaKey);
        //缓存验证码并设置过期时间
        redisUtil.setEx(captchaKey,captcha,timeout, TimeUnit.SECONDS);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaKey(captchaKey);
        captchaVO.setExpire(timeout);
        return captchaVO;
    }

}
