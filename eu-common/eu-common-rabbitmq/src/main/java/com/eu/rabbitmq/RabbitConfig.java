package com.eu.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * rabbit mq配置文件
 *
 * @author jiangxd
 */
@Slf4j
@Component
public class RabbitConfig {

    /**
     * websocket 队列
     */
    public static final String WEBSOCKET_QUEUE_NAME = "websocket_queue";
    public static final String WEBSOCKET_EXCHANGE_NAME = "websocket_exchange";
    public static final String WEBSOCKET_ROUTING_KEY = "websocket_route_key";

    /**
     * 死信 队列
     */
    public static final String DEAD_LETTER_EXCHANGE = "deadletter_exchange";
    public static final String DEAD_LETTER_QUEUE_ROUTING_KEY = "deadletter_routingkey";
    public static final String DEAD_LETTER_QUEUE_NAME = "deadletter_queue";


    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        // confirm机制只保证消息到达exchange，不保证消息可以路由到正确的queue,如果exchange错误，就会触发confirm机制
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            log.info("消息唯一标识 correlationData = {}", correlationData);
//            if (ack) {
//                log.info("消息成功发送到Exchange");
//            } else {
//                log.info("消息发送到Exchange失败, {}", correlationData);
//                log.info("失败原因 cause = {}", cause);
//            }
//        });

        // Return 消息机制用于处理一个不可路由的消息。
        // 在某些情况下，如果我们在发送消息的时候，当前的 exchange 不存在或者指定路由 key 路由不到，这个时候我们需要监听这种不可达的消息就需要这种return机制。
//        rabbitTemplate.setReturnsCallback(returned -> {
//            //  可以进行消息入库操作
//            log.info("消息从Exchange路由到Queue失败");
//            log.info("消息主体 message = {}", returned.getMessage());
//            log.info("回复码 replyCode = {}", returned.getReplyCode());
//            log.info("回复描述 replyText = {}", returned.getReplyText());
//            log.info("交换机名字 exchange = {}", returned.getExchange());
//            log.info("路由键 routingKey = {}", returned.getRoutingKey());
//        });

        // Mandatory为true时,消息通过交换器无法匹配到队列会返回给生产者，为false时匹配不到会直接被丢弃
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //设置启动spring容器时自动加载这个类(这个参数现在默认已经是true，可以不用设置)
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    // websocket Exchange
    public DirectExchange websocketMsgExchange() {
        return new DirectExchange(WEBSOCKET_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding websocketMsgBinding() {
        return BindingBuilder.bind(new Queue(WEBSOCKET_QUEUE_NAME, true)).to(websocketMsgExchange()).with(WEBSOCKET_ROUTING_KEY);
    }

    // 声明死信Exchange
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    // 声明死信队列
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE_NAME);
    }

    // 声明死信队列绑定关系
    @Bean
    public Binding deadLetterBinding(@Qualifier("deadLetterQueue") Queue queue,
                                     @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_ROUTING_KEY);
    }

}
