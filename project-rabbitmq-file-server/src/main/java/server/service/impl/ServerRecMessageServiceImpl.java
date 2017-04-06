package server.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import server.dao.InsertSingleTableDao;
import server.model.FileMessage;
import server.model.MessageClient;
import server.model.MessageServer;
import server.model.SimpleTableResult;
import server.rabbitmq.SendMessage;
import server.service.ServerRecMessageService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description
 */
@Service
@Component
public class ServerRecMessageServiceImpl implements ServerRecMessageService {

    @Value("${back.file.path}")
    private String backFilePath;

    @Autowired
    SendMessage sendMessage;

    @Autowired
    ObjectServiceImpl objectService;

    @Autowired
    InsertSingleTableDao insertSingleTableDao;

    /**
     * @param message 获得的消息
     * @param channel 监听的消息信道
     * @throws IOException
     * @throws DocumentException
     * @throws ClassNotFoundException
     * @description 服务端消息监听并处理
     */

    public void onMessage(org.springframework.amqp.core.Message message, Channel channel) throws Exception {

        byte[] body = message.getBody();
        MessageClient rec_message = JSON.parseObject(new String(body, "UTF-8"), MessageClient.class);
        List<FileMessage> fileMessages = rec_message.getMessageContents();
        if (fileMessages != null) {
            //todo 必须将接受的字符串进行数字签名验证暂时未知逻辑实施前需要补齐
            MessageService(rec_message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费
        }
    }

    /**
     *
     * @param rec_message 服务端接受到客户端发出的消息
     * 将rec_message 进行验证处理入库的流程并返回操作结果
     */
    private void MessageService(MessageClient rec_message) {
        MessageServer messageServer = new MessageServer();
        messageServer.setResult(false);
        List<FileMessage> fileMessages = rec_message.getMessageContents();
        String content = null;
        for (FileMessage messageContent : fileMessages) {

            //将接受到客户端的信息进行前置处理
            FileMessage fileMessage = messageContent;
            messageServer.setServerName(fileMessage.getBizMsgId());
            try {
                content = new String(fileMessage.getContent(),"UTF-8");
                messageServer.setPath(fileMessage.getPath());
            } catch (UnsupportedEncodingException e) {
                messageServer.setBackInfo("解析文件错误可能是消息队列问题或是客户端发送文件出现失误！");
            }
            System.out.println(content);

            //将String装换为对象
            List<Object> objs = null;
            try {
                objs = objectService.getObject(content);
            } catch (DocumentException e) {
                messageServer.setBackInfo("xml 文件解析错误！");
            } catch (ClassNotFoundException e) {
                messageServer.setBackInfo("实体对象无法定位请确认！");
            }

            //将对象插入到数据库中
            if(objs!=null){
                SimpleTableResult result = null;
                try {
                    result = insertSingleTableDao.insertSingleTable(objs);
                } catch (Exception e) {
                    messageServer.setResult(false);
                    messageServer.setBackInfo(result.getResultInfo());
                }
                if(result.isResult()){
                    messageServer.setResult(true);
                    messageServer.setBackInfo("消息成功发送！");
                }
            }

            //将处理结果发回到客户端供客户端解析处理
            sendMessage.sendDirectMsg(JSON.toJSONString(messageServer), rec_message.getQueueName());
        }
    }

}
