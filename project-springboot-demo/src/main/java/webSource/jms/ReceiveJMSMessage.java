package webSource.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/2.
 */
@Component
public class ReceiveJMSMessage {

    @JmsListener(destination = "someQueue")
    public void processMessage(String message) throws InterruptedException {
        System.out.println("we get test mail is "+message);
    }

}