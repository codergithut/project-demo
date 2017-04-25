package templates.client.rabbitmq;

import templates.client.service.impl.ClientRecMessageServiceImpl;
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

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description
 */
@Configuration
@Import(Connection.class)
public class QueueConfig {

    private String filePath="D:\\upload\\";


    @Autowired
    ConnectionFactory connectionFactory;

    public static final String EXCHANGE_DIRECT  = "exchange";

    public static final String QUEUE_CLIENT_KEY   = "dafeng";

    public static final String QUEUE_SERVER_KEY   = "server";



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
    public ClientRecMessageServiceImpl getMyMessageContainer(){
        return new ClientRecMessageServiceImpl();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    public Queue direct() {
        return new Queue(QUEUE_CLIENT_KEY, true); //队列持久
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(direct()).to(directExchange()).with(QUEUE_CLIENT_KEY);
    }


    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        MessageListenerContainer container = new MessageListenerContainer(connectionFactory);
        container.setQueues(direct());
        container.setListener(getMyMessageContainer());
        return container;
    }

}