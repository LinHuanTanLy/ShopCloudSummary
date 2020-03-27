package com.ly.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.user.entity.UserEntity;
import com.ly.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public List<UserEntity> users() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        return userMapper.selectList(queryWrapper);
    }
}
