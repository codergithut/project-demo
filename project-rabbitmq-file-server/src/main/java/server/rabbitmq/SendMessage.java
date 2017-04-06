package server.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static server.rabbitmq.QueueConfig.EXCHANGE_DIRECT;


/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 消息发送注入
 */

/**
 * 消息生产者
 *
 * @author liaokailin
 * @version $Id: Send.java, v 0.1 2015年11月01日 下午4:22:25 liaokailin Exp $
 */
@Component
public class SendMessage implements RabbitTemplate.ConfirmCallback {

    @Autowired
    SendMessage send;

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     */
    @Autowired
    public SendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }


    /**
     *
     * @param message 消息
     * @param dest 发送消息路由key
     * @description 向消息中间件发送消息处理结果的消息
     */
    public void sendDirectMsg(String message,String dest) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT, dest, message, correlationId);
    }


    /**
     *
     * @param correlationData
     * @param ack
     * @param cause
     * @description 消息回调确认消息是否成功接收
     */
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" client1  :" + correlationData);
        if (ack) {
            System.out.println("消息发送成功");
        } else {
            System.out.println("消息发送失败:"+cause);
        }
    }
}