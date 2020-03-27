package com.ly.order.controller;

import com.ly.commom.entity.ProductEntity;
import com.ly.order.client.ProductClient;
import com.ly.order.entity.OrderEntity;
import com.ly.order.vo.OrderAddVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@RestController
@Slf4j
public class OrderController {

    @Autowired
    ProductClient productClient;

    @PostMapping("/save")
    public void saveOrder(@RequestBody OrderAddVo orderAddVo) {

//        1. 查到商品信息
        int productId = orderAddVo.getProductId();
        ProductEntity productEntity = productClient.findOne(productId);
        log.info("the product is " + productEntity);

    }
}
