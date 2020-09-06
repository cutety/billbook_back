package com.cutety.entity;

import lombok.Data;

@Data
public class Response {
    private String status;
    private String msg;
    private Object object;
}
