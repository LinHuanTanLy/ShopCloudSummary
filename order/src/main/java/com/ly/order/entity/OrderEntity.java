package com.ly.order.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单
 */
@Data
public class OrderEntity implements Serializable {
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    @ApiModelProperty(value = "订单号", required = true)
    private String order_sn;
    @ApiModelProperty(value = "商品id", required = true)
    private Long product_id;
    @ApiModelProperty(value = "用户id", required = true)
    private Long user_id;
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
    private Long creator_id;
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
}