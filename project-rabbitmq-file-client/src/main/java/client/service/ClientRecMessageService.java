package client.service;

import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/31
 * @description 客户端接受消息中心的消息并处理
 */
public interface ClientRecMessageService extends ChannelAwareMessageListener {
}
