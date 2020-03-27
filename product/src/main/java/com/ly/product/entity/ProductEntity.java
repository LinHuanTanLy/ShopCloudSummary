package com.ly.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体类
 */
@TableName("app_product")
@Data
public class ProductEntity implements Serializable {
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    @ApiModelProperty(value = "商品名称", required = true)
    private String product_name;

    @ApiModelProperty(value = "编码", required = true)
    private String product_code;

    @ApiModelProperty(value = "主图url", required = true)
    private String main_img;

    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "原价", required = true)
    private BigDecimal origin_price;

    @ApiModelProperty(value = "库存数量", required = true)
    private int stock_num;

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
}
