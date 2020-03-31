package com.ly.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.commom.bean.Resp;
import com.ly.commom.entity.UserEntity;
import com.ly.commom.exception.RException;
import com.ly.user.service.EmailService;
import com.ly.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserService users;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public List<UserEntity> users() {
        return users.list();
    }


    @GetMapping("/{userId}")
    public UserEntity user(@PathVariable String userId) {
        return users.getById(userId);
    }

    @GetMapping("/{userName}")
    public UserEntity userByUserName(@PathVariable String userName) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        return users.getOne(queryWrapper);
    }

    @PutMapping("/")
    public boolean update(@RequestBody UserEntity userEntity) {
        return users.updateUser(userEntity);
    }


    @PostMapping("/register")
    public Resp<String> register(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String password,
                                 @RequestParam(value = "verCode") String code) {

        boolean result = users.register(userName, password, code);
        if (result) {
            return Resp.ok("注册成功");
        } else {
            return Resp.fail("注册失败");
        }
    }

    @PostMapping("/login")
    public Resp<UserEntity> login(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String password) {
        return Resp.ok(users.login(userName, password));
    }


    /**
     * 发送验证码
     *
     * @param to
     * @return
     */
    @PostMapping("/sendEmail")
    public Resp<String> sendVerificationSimpleMail(@RequestParam(value = "to") String to) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", to);
        int count = users.count(wrapper);
        if (count != 0) {
            throw new RException("该用户已经存在");
        }
        boolean result = emailService.sendVerificationSimpleMail(to);
        if (result) {
            return Resp.ok("发送成功");
        } else {
            return Resp.fail("发送失败");
        }
    }


}
