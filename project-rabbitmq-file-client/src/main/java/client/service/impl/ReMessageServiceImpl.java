package client.service.impl;

import client.model.ResponseMessage;
import client.service.LoggerService;
import client.util.FileUtil;
import client.util.JaxbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/31
 * @description 接受反馈消息的实现类
 */

@Service
public class ReMessageServiceImpl {

    @Value("${xml.response.path}")
    private String responsePath;

    @Autowired
    LoggerService loggerService;


    public boolean saveInfoByMessage(ResponseMessage responseMessage, String path) {

        //todo 根据ResponseMessage构建Logger对象并将数据插入到数据库中去
        //loggerService.insertLoggerInfo(new Logger());

        if("0000".equals(responseMessage.getResponseCode())) {
            //todo 实际情况需要开启删除服务
            //FileUtil.removeFile(path);
        } else {
            //todo 将文件夹下的文件移动到errror文件夹下或者直接删除待定
        }

        return saveInfoByMessage(responseMessage);
    }

    private boolean saveInfoByMessage(ResponseMessage responseMessage) {

        saveResponseXMlInfo(responseMessage);

        if("0000".equals(responseMessage.getResponseCode())){


            //TODO:将数据塞到数据库中
        }
        return true;
    }

    private boolean saveResponseXMlInfo(ResponseMessage responseMessage){
        try {
            FileUtil.saveFile(JaxbUtil.convertToXml(responseMessage).getBytes(),responsePath+responseMessage.getBizMsgId()+".xml");
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
