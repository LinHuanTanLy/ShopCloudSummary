package com.ly.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.commom.entity.UserEntity;
import com.ly.commom.exception.RException;
import com.ly.commom.utils.CommUtils;
import com.ly.user.mapper.UserMapper;
import com.ly.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public boolean updateUser(UserEntity userEntity) {
        return userMapper.updateUser(userEntity) == 1;
    }

    @Override
    public boolean register(String userName, String passWord, String verificationCode) {
        String redisCode = redisTemplate.opsForValue().get(userName);
        log.info("从redis获取验证码为" + redisCode);
        if (StringUtils.isEmpty(redisCode) || !verificationCode.equals(redisCode)) {
            throw new RException("验证码错误");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_name(userName);
        userEntity.setPassword(CommUtils.encode(passWord));
        return 1 == userMapper.insert(userEntity);
    }


}
