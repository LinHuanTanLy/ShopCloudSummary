package com.ly.auth.client;


import com.ly.auth.entity.Resp;
import com.ly.auth.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserClient {
    @PostMapping("/login")
    Resp<UserEntity> login(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String password);
}
