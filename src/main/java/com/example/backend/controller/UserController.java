package com.example.backend.controller;

import com.example.backend.dao.UserMapper;
import com.example.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public Map<String, Integer> login(String userName, String password) {
        List<User> list = userMapper.login(userName, password);
        Map<String, Integer> map = new HashMap<String,Integer>();
        map.put("status", list.size());
        if (!map.isEmpty()) {
            map.put("userId", list.get(0).getUserId());
        }
        return map;
    }
}
