package com.ly.order.controller;

import com.ly.commom.bean.Resp;
import com.ly.commom.entity.ProductEntity;
import com.ly.order.client.ProductClient;
import com.ly.order.service.OrderService;
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

    @Autowired
    OrderService orderService;

    @PostMapping("/save")
    public Resp<String> saveOrder(@RequestBody OrderAddVo orderAddVo) {
        orderService.createOrder(orderAddVo);
        return Resp.ok("下单成功");
//        1. 查到商品信息
//        int productId = orderAddVo.getProductId();
//        ProductEntity productEntity = productClient.findOne(productId);
//        log.info("the product is " + productEntity);

    }
}
