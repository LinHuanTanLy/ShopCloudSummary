package com.ly.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.commom.entity.UserEntity;

public interface UserService extends IService<UserEntity> {


    public boolean updateUser(UserEntity userEntity);


    boolean register(String userName, String passWord, String verificationCode);
}
