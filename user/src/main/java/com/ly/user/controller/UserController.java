package com.ly.user.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ly.commom.bean.Resp;
import com.ly.commom.entity.UserEntity;
import com.ly.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    UserService users;

    @GetMapping("/")
    public List<UserEntity> users() {
        return users.list();
    }


    @GetMapping("/{userId}")
    public UserEntity user(@PathVariable String userId) {
        return users.getById(userId);
    }

    @PutMapping("/")
    @Transactional
    public boolean update(@RequestBody UserEntity userEntity) {
        return users.updateUser(userEntity);
    }
}
