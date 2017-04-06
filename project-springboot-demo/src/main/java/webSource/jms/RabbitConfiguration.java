package webSource.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/12/1.
 */
@Configuration
public class RabbitConfiguration {

//    /**
//     *
//     * @return自定义AMQP的各项参数实例
//     */
//    @Bean
//    public ConnectionFactory myConnectionFactory(){
//        return new ConnectionFactory() {
//            @Override
//            public Connection createConnection() throws AmqpException {
//                return null;
//            }
//
//            @Override
//            public String getHost() {
//                return null;
//            }
//
//            @Override
//            public int getPort() {
//                return 0;
//            }
//
//            @Override
//            public String getVirtualHost() {
//                return null;
//            }
//
//            @Override
//            public String getUsername() {
//                return null;
//            }
//
//            @Override
//            public void addConnectionListener(ConnectionListener connectionListener) {
//
//            }
//
//            @Override
//            public boolean removeConnectionListener(ConnectionListener connectionListener) {
//                return false;
//            }
//
//            @Override
//            public void clearConnectionListeners() {
//
//            }
//        };
//    }
//
//    /**
//     * @return自定义消息转换规则
//     */
//
//    @Bean
//    public MessageConverter myMessageConverter(){
//        return new MessageConverter() {
//            @Override
//            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
//                return null;
//            }
//
//            @Override
//            public Object fromMessage(Message message) throws MessageConversionException {
//                return null;
//            }
//        };
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory myFactory(
//            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
//        SimpleRabbitListenerContainerFactory factory =
//                new SimpleRabbitListenerContainerFactory();
////        configurer.configure(factory, myConnectionFactory());
////        factory.setMessageConverter(myMessageConverter());
//        return factory;
//    }

//    @Bean
//    public CachingConnectionFactory myConnectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/myHost");
//        return connectionFactory;
//    }

}