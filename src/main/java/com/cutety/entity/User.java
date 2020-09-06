package com.cutety.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class User {
    private Integer id;

    private String username;

    private String password;

    private String salt;
}