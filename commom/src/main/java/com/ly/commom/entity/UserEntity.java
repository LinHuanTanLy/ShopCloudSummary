package com.ly.commom.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户数据表
 */
@Data
@TableName("app_user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -2071565876962058344L;
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键ID", required = true)
    private Integer id;
    @ApiModelProperty(value = "用户名称", required = true)
    private String user_name;
    @ApiModelProperty(value = "用户密码", required = true)
    private String password;
    @ApiModelProperty(value = "真实姓名", required = true)
    private String real_name;
    @ApiModelProperty(value = "用户头像", required = true)
    private String user_avatar;
    @ApiModelProperty(value = "手机号码", required = true)
    private String user_mobile;
    @ApiModelProperty(value = "用户来源  0：默认  1：企业微信  2：微信  3：QQ", required = true)
    private Integer user_source;
    @ApiModelProperty(value = "用户来源标识，如企业微信成员id/微信openid", required = true)
    private String user_source_ident;
    @ApiModelProperty(value = "用户状态：0：正常 1：锁定   2：禁用", required = true)
    private Integer user_status;
    @ApiModelProperty(value = "微信openId")
    private String open_id;
    @ApiModelProperty(value = "用户所属部门ID", required = true)
    private Integer dept_id;
    @ApiModelProperty(value = "删除标记0正常1删除", required = true)
    private Integer is_deleted;
    @ApiModelProperty(value = "版本号", required = true)
    private Integer version;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "创建者ID")
    private Long creator_id;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created_time;
    @ApiModelProperty(value = "更新用户")
    private String last_operator;
    @ApiModelProperty(value = "更新用户Id")
    private Integer last_operator_id;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;
    @ApiModelProperty(value = "历史订单数")
    private Integer his_order_quantity;
    @ApiModelProperty(value = "用户金额")
    private BigDecimal balance;
}
