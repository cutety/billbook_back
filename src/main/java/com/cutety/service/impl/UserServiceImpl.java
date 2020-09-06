package com.cutety.service.impl;

import com.cutety.entity.User;
import com.cutety.mapper.UserMapper;
import com.cutety.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public int ifUsernameExist(String username) {
        return userMapper.ifUsernameExist(username);
    }


}
