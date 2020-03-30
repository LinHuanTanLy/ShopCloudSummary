package com.ly.order.controller;

import com.ly.commom.bean.Resp;
import com.ly.commom.conf.RabbitMQConfig;
import com.ly.commom.utils.rabbitUtils.ConnectionUtil;
import com.ly.order.client.ProductClient;
import com.ly.order.service.OrderService;
import com.ly.order.vo.OrderAddVo;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@RestController
@Slf4j
public class OrderController {

    @Autowired
    ProductClient productClient;

    @Autowired
    OrderService orderService;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @PostMapping("/save")
    public Resp<String> saveOrder(@RequestBody OrderAddVo orderAddVo) {
        orderService.createOrder(orderAddVo);
        return Resp.ok("下单成功");
    }


    @PostMapping("/sendMsg")
    public Resp<String> sendEmail(@RequestParam("msgStr") String emailStr) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.EMAIL_ROUTEKEY, emailStr);
            return Resp.ok("发送成功");
        } catch (AmqpException e) {
            e.printStackTrace();
            return Resp.ok("发送失败");
        }
    }



}
