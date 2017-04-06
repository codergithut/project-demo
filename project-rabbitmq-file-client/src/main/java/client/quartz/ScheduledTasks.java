package client.quartz;

import client.service.impl.FileSendServiceImpl;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/2/13
 * @description 定时器启动到指定文件夹下获取数据并上传到消费接受中心
 */
@Component
@Configurable
public class ScheduledTasks{

    @Autowired
    FileSendServiceImpl fileService;
    public void reportCurrentTime() throws DocumentException, IOException {
        System.out.println("service is begin");
        fileService.handleFileUpload();
        System.out.println("Service is end");
    }


    public void reportCurrentByCron() throws DocumentException, IOException {
        System.out.println("service is begin");
        fileService.handleFileUpload();
        System.out.println("Service is end");
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }

}