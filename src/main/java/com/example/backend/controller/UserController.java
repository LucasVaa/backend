package com.example.backend.controller;

import com.example.backend.dao.UserMapper;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/query")
    public List<User> queryAll() {
        return userMapper.queryAll();
    }
}
