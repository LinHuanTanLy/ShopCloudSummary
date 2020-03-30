package com.ly.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单添加vo
 */
@Data
public class OrderAddVo implements Serializable {
    @NotNull(message = "订单id不可以为空")
    @ApiModelProperty(value = "商品id", required = true)
    private Integer product_id;
    @NotNull(message = "用户id不可以为空")
    @ApiModelProperty(value = "用户id", required = true)
    private Integer user_id;
    @NotNull(message = "下单数量不可以为空")
    @ApiModelProperty(value = "数量", required = true)
    private Integer product_num;
    @NotNull(message = "店铺ID不可以为空")
    @ApiModelProperty(value = "店铺ID", required = true)
    private Integer store_id;
    @NotNull(message = "店铺编码不可以为空")
    @ApiModelProperty(value = "店铺编码", required = true)
    private String store_code;
    @NotNull(message = "金额不可以为空")
    @ApiModelProperty(value = "金钱")
    private BigDecimal price;
}
