package com.ly.user.mapper.provider;

import com.ly.commom.constant.ShopConstant;
import com.ly.commom.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * user专用sql
 */
@Slf4j
public class UserProvider extends SQL {

    private static final String T_USER = "APP_USER";


    /**
     * 根据条件进行更新
     *
     * @param userUpdateVo
     * @return
     */
    public String updateUser(UserEntity userUpdateVo) {
        String userSql = new SQL() {
            {
                UPDATE(T_USER);
                if (!StringUtils.isEmpty(userUpdateVo.getUser_name())) {
                    SET("user_name = #{user_name}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getPassword())) {
                    SET("password= #{password}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getReal_name())) {
                    SET("real_name = #{real_name}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUser_avatar())) {
                    SET("user_avatar = #{user_avatar}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUser_mobile())) {
                    SET("user_mobile = #{user_mobile}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUser_status())) {
                    SET("user_status = #{user_status}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getOpen_id())) {
                    SET("open_id = #{open_id}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getDept_id())) {
                    SET("dept_id = #{dept_id}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUser_source())) {
                    SET("user_source = #{user_source}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUser_source_ident())) {
                    SET("user_source_ident = #{user_source_ident}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getIs_deleted())) {
                    SET("is_deleted = #{is_deleted}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getHis_order_quantity())) {
                    SET("his_order_quantity = #{his_order_quantity}");
                }
                SET("update_time =  #{update_time}");
                SET("last_operator_id =  #{last_operator_id}");
                SET("last_operator =  #{last_operator}");
                WHERE("id = #{id}");
            }
        }.toString();
        log.info("in the method ,the sql is " + userSql);
        return userSql;
    }
}
