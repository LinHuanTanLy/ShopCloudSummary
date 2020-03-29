package com.ly.order.client;


import com.ly.commom.bean.Resp;
import com.ly.commom.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/{userId}")
    UserEntity user(@PathVariable Integer userId);

    @PutMapping("/")
    boolean update(@RequestBody UserEntity userEntity);
}
