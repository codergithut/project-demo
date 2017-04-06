package webSource.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import webSource.rabbitmq.model.FileMessage;
import webSource.rabbitmq.model.Message;
import webSource.rabbitmq.rabbit.SendRabbitMessage;
import webSource.rabbitmq.utils.FileUtil;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FileService {

    @Value("${source.file.path}")
    private String getFilePath;

    @Autowired
    SendRabbitMessage sendMessage;


    public void handleFileUpload() throws DocumentException, IOException {
        List<File> files = FileUtil.getFiles(getFilePath);
        Message message = new Message();
        List<FileMessage> fileContents = new ArrayList<FileMessage>();
        List<FileMessage> errors = new ArrayList<FileMessage>();
        Map<String,String> contents = null;
        String content;
        if (files.size() > 0) {
            for (File file : files) {
                FileMessage fileMessage = new FileMessage();
                String fileName = file.getName();
                if (file != null) {
                    contents = ScannerXmlFile.getXmlFile(file);
                    content = contents.get("content");
                    if (content!=null && !"null".equals(contents.get("content"))) {
                        fileMessage.setContent(content.getBytes());
                        fileMessage.setFileName(fileName);
                        fileContents.add(fileMessage);
                        message.setForm(contents.get("AreaCode"));
                    } else {
                        fileMessage.setContent(("数据验证有误" + file.getName()).getBytes());
                        errors.add(fileMessage);
                    }
                }
            }
            if(fileContents.size()>0){
                message.setMessageContents(fileContents);
                sendMessage.sendDirectMsg(JSON.toJSONString(message));
            }

            if(errors.size()>0){
                sendMessage.sendErrortMsg(JSON.toJSONString(errors));
            }
        }
    }

}