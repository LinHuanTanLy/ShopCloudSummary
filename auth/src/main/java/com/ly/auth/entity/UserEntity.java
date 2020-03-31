package com.ly.auth.entity;

import lombok.Data;

import javax.management.relation.Role;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户数据表
 */
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -2071565876962058344L;
    private Integer id;
    private String user_name;
    private String password;
    private String real_name;
    private String user_avatar;
    private String user_mobile;
    private Integer user_source;
    private String user_source_ident;
    private Integer user_status;
    private String open_id;
    private Integer dept_id;
    private Integer is_deleted;
    private Integer version;
    private String creator;
    private Long creator_id;
    private LocalDateTime created_time;
    private String last_operator;
    private Integer last_operator_id;
    private LocalDateTime update_time;
    private Integer his_order_quantity;
    private BigDecimal balance;
}
