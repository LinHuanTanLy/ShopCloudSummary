package com.ly.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.commom.entity.UserEntity;
import com.ly.user.mapper.UserMapper;
import com.ly.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean updateUser(UserEntity userEntity) {
        return userMapper.updateUser(userEntity) == 1;
    }
}
