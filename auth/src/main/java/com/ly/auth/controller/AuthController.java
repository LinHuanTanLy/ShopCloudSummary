package com.ly.auth.controller;


import com.ly.auth.entity.Resp;
import com.ly.auth.entity.UserEntity;
import com.ly.auth.jwt.JwtProperties;
import com.ly.auth.service.AuthService;
import com.ly.auth.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    private JwtProperties properties;

    @PostMapping("/login")
    Resp<String> login(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String password,
                       HttpServletRequest request, HttpServletResponse response) {
        String token = authService.accredit(userName, password);
        if (!StringUtils.isEmpty(token)) {
            CookieUtils.setCookie(request, response, this.properties.getCookieName(),
                    token, this.properties.getExpire() * 60);
            return Resp.ok("登录成功");
        }
        return Resp.fail("登录失败");
    }
}
