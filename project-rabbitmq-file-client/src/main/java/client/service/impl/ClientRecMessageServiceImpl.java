package client.service.impl;

import client.model.MessageServer;
import client.model.ResponseMessage;
import client.service.ClientRecMessageService;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 客户端接受消息实现类
 */
@Service
@Component
public class ClientRecMessageServiceImpl implements ClientRecMessageService {

    @Value("${back.file.path}")
    private String backFilePath;

    @Autowired
    ReMessageServiceImpl messageService;

    public ClientRecMessageServiceImpl(){
    }

    public void onMessage(org.springframework.amqp.core.Message message, Channel channel) throws IOException, InterruptedException {
        byte[] body = message.getBody();
        ResponseMessage responseMessage = new ResponseMessage();

        MessageServer rec_message= JSON.parseObject(new String(body), MessageServer.class);
        //todo:服务器信息预先处理
        System.out.println("路径为:" + rec_message.getPath() + "结果为:" + rec_message.isResult());

        if(rec_message.isResult()){
            responseMessage.setResponseCode("0000");
            responseMessage.setBizMsgId(rec_message.getServerName());
            responseMessage.setSuccessFlag("true");
        }else{
            //TODO 根据错误消息封装对应的对象并将玩意写到文件中去
            responseMessage.setResponseCode("2000");
            responseMessage.setBizMsgId(rec_message.getServerName());
            responseMessage.setSuccessFlag("false");
            responseMessage.setResponseInfo(rec_message.getBackInfo());
        }

        messageService.saveInfoByMessage(responseMessage, rec_message.getPath());

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费
    }


}
