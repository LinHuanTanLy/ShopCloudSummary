package com.ly.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.commom.entity.UserEntity;
import com.ly.user.mapper.provider.UserProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface UserMapper extends BaseMapper<UserEntity> {

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    int updateUser(UserEntity userUpdateVo);
}
