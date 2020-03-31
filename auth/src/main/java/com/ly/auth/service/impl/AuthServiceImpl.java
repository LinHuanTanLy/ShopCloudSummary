package com.ly.auth.service.impl;

import com.ly.auth.client.UserClient;
import com.ly.auth.entity.Resp;
import com.ly.auth.entity.UserEntity;
import com.ly.auth.jwt.JwtProperties;
import com.ly.auth.service.AuthService;
import com.ly.auth.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserClient userClient;
    @Autowired
    private JwtProperties properties;

    @Override
    public String accredit(String userName, String passWord) {
        UserEntity userEntity = userClient.login(userName, passWord).getData();
        log.info("AuthServiceImpl--the userEntity is " + userEntity);
        try {
            // 制作jwt
            Map<String, Object> map = new HashMap<>();
            map.put("id", userEntity.getId());
            map.put("username", userEntity.getUser_name());
            String token = JwtUtils.generateToken(map, properties.getPrivateKey(), properties.getExpire());
            log.info("AuthServiceImpl--the token is " + token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("AuthServiceImpl--the Exception is " + e.getMessage());
            return null;
        }
    }
}
