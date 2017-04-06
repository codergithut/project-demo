package webSource.quartz;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import webSource.rabbitmq.service.FileService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/22.
 */
@Component
@Configurable
public class ScheduledTasks{

    @Autowired
    FileService fileService;
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