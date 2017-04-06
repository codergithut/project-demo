package client.service.impl;

import client.common.CommonConfiguration;
import client.model.FileMessage;
import client.model.MessageClient;
import client.model.ResponseMessage;
import client.rabbitmq.SendMessage;
import client.service.CheckXmlValidateService;
import client.util.FileUtil;
import client.util.XmlUtil;
import com.alibaba.fastjson.JSON;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static client.rabbitmq.QueueConfig.QUEUE_CLIENT_KEY;



/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 文件处理实现类
 */
@Service
public class FileSendServiceImpl extends CommonConfiguration {

    @Value("${source.file.path}")
    private String getFilePath;

    @Value("${back.file.path}")
    private String backFilePath;


    @Autowired
    SendMessage sendMessage;

    @Autowired
    ReMessageServiceImpl messageService;

    @Autowired
    CheckXmlValidateService checkXmlValidateService;

    public void handleFileUpload() {
        List<File> files = FileUtil.getFiles(getFilePath);
        MessageClient message = new MessageClient();
        List<FileMessage> fileContents = new ArrayList<FileMessage>();
        message.setQueueName(QUEUE_CLIENT_KEY);
        message.setForm(AREA_NAME);
        if (files.size() > 0) {
            for (File file : files) {
                FileMessage fileMessage = new FileMessage();
                String fileName = file.getName();
                if (file != null) {


                    //测试用数据
//                    fileMessage.setContent(FileUtil.getFileString(file).getBytes());
//                    FileUtil.saveFile(FileUtil.getFileString(file).getBytes(), backFilePath + fileName);
//                    fileMessage.setPath(backFilePath + file.getName());
//                    fileContents.add(fileMessage);

                    String filePath = file.getPath();
                    String fileString= FileUtil.getFileString(file);
                    Map<String,String> head = null;
                    try {
                        head = XmlUtil.getParamValueByElement(fileString,"Head");
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    String serverNumber = null;
                    if(head!=null){
                        serverNumber = head.get("BizMsgID");
                    }

                    boolean flag = checkXmlValidateService.CheckXmlFile(fileString, serverNumber);

                    if(!flag){
                        fileMessage.setContent(fileString.getBytes());
                        fileMessage.setPath(filePath);
                        fileMessage.setFileName(fileName);
                        fileMessage.setBizMsgId(serverNumber);
                        fileContents.add(fileMessage);
                        try {
                            FileUtil.saveFile(FileUtil.getFileString(file).getBytes(), backFilePath + fileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ResponseMessage responseMessage = new ResponseMessage();
                        responseMessage.setBizMsgId(serverNumber);
                        responseMessage.setResponseInfo("消息验证失败");
                        responseMessage.setResponseCode("1000");
                        messageService.saveInfoByMessage(responseMessage, filePath);
                    }

                    //todo 实际情况需要打开为方便测试不删除当前测试文件
                    //FileUtil.removeFile(filePath);


                    //真实数据处理流程
//                    contents = CheckXmlValidateServiceImpl.getXmlFile(file);
//                    content = contents.get("content");
//                    if (content != null && !"null".equals(contents.get("content"))) {
//                        fileMessage.setContent(content.getBytes());
//                        fileMessage.setPath(file.getPath());
//                        fileMessage.setFileName(fileName);
//                        fileContents.add(fileMessage);
//                        message.setForm(contents.get("AreaCode"));
//                        message.setQueueName(EXCHANGE_DIRECT_CENTER);
//                    } else {
//                        fileMessage.setContent(("数据验证有误" + file.getName()).getBytes());
//                        errors.add(fileMessage);
//                    }
                }
            }
            if(fileContents.size()>0){
                message.setMessageContents(fileContents);
                sendMessage.sendDirectMsg(JSON.toJSONString(message));
            }
        }
    }

}