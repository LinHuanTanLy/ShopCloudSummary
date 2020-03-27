package com.ly.user.controller;

import com.ly.user.entity.UserEntity;
import com.ly.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService users;

    @GetMapping("/")
    public List<UserEntity> users() {
        return users.list();
    }
}
