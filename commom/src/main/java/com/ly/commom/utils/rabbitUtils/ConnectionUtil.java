package com.ly.commom.utils.rabbitUtils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * rabbitmq 连接工具类
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("ly-test");
        factory.setUsername("ly");
        factory.setPassword("ly");
        // 通过工程获取连接
        return factory.newConnection();
    }
}
