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
 * 订单
 */
@TableName("app_order")
@Data
public class OrderEntity implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键ID", required = true)
    private Integer id;
    @ApiModelProperty(value = "订单号", required = true)
    private String order_sn;
    @ApiModelProperty(value = "商品id", required = true)
    private Integer product_id;
    @ApiModelProperty(value = "用户id", required = true)
    private Integer user_id;
    @ApiModelProperty(value = "数量", required = true)
    private Integer product_num;
    @ApiModelProperty(value = "是否删除 0-未删除 1-删除", required = true)
    private Integer is_deleted;
    @ApiModelProperty(value = "店铺ID", required = true)
    private Integer store_id;
    @ApiModelProperty(value = "店铺编码", required = true)
    private String store_code;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "创建者ID")
    private Integer creator_id;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created_time;
    @ApiModelProperty(value = "更新用户")
    private String last_operator;
    @ApiModelProperty(value = "更新用户Id")
    private Integer last_operator_id;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;
    @ApiModelProperty(value = "订单状态 0-未支付 1-已支付 2-已取消 3-已退款")
    private Integer status;
    @ApiModelProperty(value = "金钱")
    private BigDecimal price;
}