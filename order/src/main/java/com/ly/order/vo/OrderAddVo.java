package com.ly.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单添加vo
 */
@Data
public class OrderAddVo {
    @ApiModelProperty(value = "商品id", required = true)
    private Integer product_id;
    @ApiModelProperty(value = "用户id", required = true)
    private Integer user_id;
    @ApiModelProperty(value = "数量", required = true)
    private Integer product_num;
    @ApiModelProperty(value = "店铺ID", required = true)
    private Integer store_id;
    @ApiModelProperty(value = "店铺编码", required = true)
    private String store_code;
    @ApiModelProperty(value = "金钱")
    private BigDecimal price;
}
