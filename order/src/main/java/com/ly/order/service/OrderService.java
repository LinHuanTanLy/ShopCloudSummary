package com.ly.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.commom.entity.OrderEntity;
import com.ly.order.vo.OrderAddVo;

public interface OrderService extends IService<OrderEntity> {


    public void createOrder(OrderAddVo orderAddVo);
}
