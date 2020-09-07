package com.cutety.entity;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;

    private String password;

    private String captchaKey;

    private String captcha;

}
