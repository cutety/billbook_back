package com.cutety.controller;


import com.cutety.entity.CaptchaVO;
import com.cutety.service.impl.CaptchaService;
import com.cutety.utils.RedisUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class CaptchaController {
    public static Logger log = LoggerFactory.getLogger(CaptchaController.class);
    private final DefaultKaptcha producer;

    private final CaptchaService captchaService;

    private final RedisUtil redisUtil;
    public CaptchaController(DefaultKaptcha producer, CaptchaService captchaService, RedisUtil redisUtil) {
        this.producer = producer;
        this.captchaService = captchaService;
        this.redisUtil = redisUtil;
    }

    @ResponseBody
    @GetMapping(value = {"/captcha/{captchaKey}"})
    public CaptchaVO getCaptcha(@PathVariable("captchaKey") String captchaKey) throws IOException {
        if (!captchaKey.equals("init")) {
            log.info("删除验证码:{}",captchaKey);
            redisUtil.delete(captchaKey);
        }
        // 生成文字验证码
        String content = producer.createText();
        log.info("captcha was created value is :{}", content);
        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(content);

        outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray()).replace("\n", "").replace("\r", "");

        CaptchaVO captchaVO = captchaService.cacheCaptcha(content);
        captchaVO.setBase64Img(base64Img);

        return captchaVO;
    }

}

