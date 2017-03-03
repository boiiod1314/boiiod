package com.boiiod.service.impl;

import com.boiiod.mapper.UserMapper;
import com.boiiod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;


    public UserMapper getUserMapper() {
        return this.userMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}