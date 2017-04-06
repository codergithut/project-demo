package webSource.rtx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.JMSException;
import java.security.Principal;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/1/22
 * @description
 */
@Controller
public class GreetingController {

    @Autowired
    SimpMessageSendingOperations messageTemp;

    @RequestMapping("/helloSocket")
    public String index(){
        return "chart/websocket";
    }


    //根据文本内容简单的把文本发给提到的用户
    @MessageMapping("/change-notice")
    public void greeting(String value,Principal principal) throws InterruptedException, JMSException {
        if(value.contains("user")){
            messageTemp.convertAndSendToUser("user","/queue/notifications",value);
        }
        if(value.contains("admin")){
            messageTemp.convertAndSendToUser("admin","/queue/notifications",value);
        }

    }



}