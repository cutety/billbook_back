package com.cutety.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String status;
    private String msg;
    private Object object;
    private UsernamePasswordToken token;
}
