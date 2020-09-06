package com.cutety.service;

import com.cutety.entity.User;

public interface UserService {
    int addUser(User user);
    User getByUsername(String username);
    int ifUsernameExist(String username);
}
