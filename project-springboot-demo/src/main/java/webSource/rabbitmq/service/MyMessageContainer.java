package webSource.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import webSource.rabbitmq.model.FileMessage;
import webSource.rabbitmq.model.Message;
import webSource.rabbitmq.utils.FileUtil;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/13
 * @description
 */
@Service
@Component
public class MyMessageContainer implements ChannelAwareMessageListener {

    @Value("${back.file.path}")
    private String backFilePath;

    public MyMessageContainer(){
    }

    @Override
    public void onMessage(org.springframework.amqp.core.Message message, Channel channel) throws IOException {
        byte[] body = message.getBody();
        Message rec_message= JSON.parseObject(new String(body),Message.class);
        //todo:服务器信息预先处理
        List<FileMessage> fileMessages=rec_message.getMessageContents();
        if(fileMessages!=null){
            for(FileMessage messageContent:fileMessages){
                FileMessage fileMessage=(FileMessage)messageContent;
                FileUtil.saveFile(fileMessage.getContent(),backFilePath+fileMessage.getFileName());
            }
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费
    }


}
