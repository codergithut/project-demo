package webSource.rabbitmq.rabbit;

import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/14
 * @description
 */
public class MessageListenerContainer extends SimpleMessageListenerContainer {
    public static final String SERVICE = "sprigboot-test";
    Queue queue;
    public MessageListenerContainer(ConnectionFactory connectionFactory,
                                    Queue queue, ChannelAwareMessageListener listener, AbstractExchange exchange){
        super(connectionFactory);
        this.setQueues(queue);
        this.queue=queue;
        this.setExposeListenerChannel(true);
        this.setMaxConcurrentConsumers(1);
        this.setConcurrentConsumers(1);
        this.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        this.setMessageListener(listener);
        BindingBuilder.bind(queue).to(exchange).with(SERVICE);
    }

    public MessageListenerContainer(ConnectionFactory connectionFactory){
        super(connectionFactory);
        this.setExposeListenerChannel(true);
        this.setMaxConcurrentConsumers(1);
        this.setConcurrentConsumers(1);
        this.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    }
    public MessageListenerContainer setListener(ChannelAwareMessageListener listener){
        this.setMessageListener(listener);
        return this;
    }
}
