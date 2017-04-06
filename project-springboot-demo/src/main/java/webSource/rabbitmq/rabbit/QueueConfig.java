package webSource.rabbitmq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import webSource.rabbitmq.service.MyMessageContainer;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/10
 * @description
 */
@Configuration
@Import(Connection.class)
public class QueueConfig {

    private String filePath="D:\\upload\\";


    @Autowired
    ConnectionFactory connectionFactory;


    public static final String EXCHANGE_DIRECT   = "spring-direct";

    public static final String QUEUE_ERROR   = "queue-error";

    public static final String QUEUE_SUCCESS   = "queue-success";

    public static final String SUCCESS = "success";

    public static final String QUEUE_BACKINFO = "back-info";

    public static final String ERROR = "error";



    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */

    @Bean
    public MyMessageContainer getMyMessageContainer(){
        return new MyMessageContainer();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_SUCCESS, true); //队列持久

    }

    @Bean
    public Queue ErrorQueue() {
        return new Queue(QUEUE_ERROR, true); //队列持久
    }

    @Bean
    public Queue BackInfoQueue() {
        return new Queue(QUEUE_BACKINFO, true); //队列持久
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(SUCCESS);
    }

    @Bean
    public Binding directErrorBinding() {
        return BindingBuilder.bind(ErrorQueue()).to(directExchange()).with(ERROR);
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        MessageListenerContainer container = new MessageListenerContainer(connectionFactory);
        container.setQueues(BackInfoQueue());
       // container.setListener(getMyMessageContainer());
        return container;
    }



}