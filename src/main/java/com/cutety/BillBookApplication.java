package com.cutety;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.cutety.mapper")
@ImportResource(locations = {"classpath:kaptcha/Kaptcha.xml"})
public class BillBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillBookApplication.class, args);
    }
}
