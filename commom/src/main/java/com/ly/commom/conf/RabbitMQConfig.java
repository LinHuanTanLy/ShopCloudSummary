package com.ly.commom.conf;

import com.rabbitmq.client.ConnectionFactory;

import io.seata.common.util.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@ComponentScan(basePackages = {"com.ly.commom"})
@PropertySource(value = {"classpath:rabbit.yml"})
public class RabbitMQConfig {

    @Autowired
    private Environment env;
    public static String EMAIL_EXCHANGE = "email_exchange";
    public static String EMAIL_ROUTEKEY = "email_routekey";

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Objects.requireNonNull(env.getProperty("mq.host")).trim());
        connectionFactory.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("mq.port")).trim()));
        connectionFactory.setVirtualHost(Objects.requireNonNull(env.getProperty("mq.vhost")).trim());
        connectionFactory.setUsername(Objects.requireNonNull(env.getProperty("mq.username")).trim());
        connectionFactory.setPassword(Objects.requireNonNull(env.getProperty("mq.password")).trim());
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(cachingConnectionFactory());
    }

    @Bean
    Queue queue() {
        String name = env.getProperty("mq.queue").trim();
        // 是否持久化
        boolean durable = !StringUtils.isNotBlank(Objects.requireNonNull(env.getProperty("mq.queue.durable")).trim()) || Boolean.parseBoolean(Objects.requireNonNull(env.getProperty("mq.queue.durable")).trim());
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = StringUtils.isNotBlank(Objects.requireNonNull(env.getProperty("mq.queue.exclusive")).trim()) && Boolean.parseBoolean(Objects.requireNonNull(env.getProperty("mq.queue.exclusive")).trim());
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(Objects.requireNonNull(env.getProperty("mq.queue.autoDelete")).trim()) && Boolean.parseBoolean(Objects.requireNonNull(env.getProperty("mq.queue.autoDelete")).trim());
        return new Queue(name, durable, exclusive, autoDelete);
    }

    @Bean
    TopicExchange exchange() {
        String name = Objects.requireNonNull(env.getProperty("mq.exchange")).trim();
        // 是否持久化
        boolean durable = !StringUtils.isNotBlank(Objects.requireNonNull(env.getProperty("mq.exchange.durable")).trim()) || Boolean.parseBoolean(Objects.requireNonNull(env.getProperty("mq.exchange.durable")).trim());
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(Objects.requireNonNull(env.getProperty("mq.exchange.autoDelete")).trim()) && Boolean.parseBoolean(Objects.requireNonNull(env.getProperty("mq.exchange.autoDelete")).trim());
        return new TopicExchange(name, durable, autoDelete);
    }

    @Bean
    Binding binding() {
        String routekey = Objects.requireNonNull(env.getProperty("mq.routekey")).trim();
        return BindingBuilder.bind(queue()).to(exchange()).with(routekey);
    }
}