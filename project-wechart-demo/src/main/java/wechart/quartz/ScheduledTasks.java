package wechart.quartz;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/12/22.
 */
@Component
@Configurable
public class ScheduledTasks{

//    @Autowired
//    private CoreService coreService;
    public void reportCurrentTime(){
        System.out.println("service is begin");
//        coreService.processRequest(null);
        System.out.println("Service is end");
    }


    public void reportCurrentByCron(){
        System.out.println("service is begin");
//        coreService.processRequest(null);
        System.out.println("Service is end");
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }

}