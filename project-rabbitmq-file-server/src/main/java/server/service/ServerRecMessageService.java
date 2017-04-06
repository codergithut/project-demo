package server.service;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/4/1
 * @description 接受客户端发送消息的逻辑处理
 */
public interface ServerRecMessageService extends ChannelAwareMessageListener {
}
