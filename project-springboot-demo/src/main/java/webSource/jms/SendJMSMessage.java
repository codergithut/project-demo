package webSource.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import javax.jms.JMSException;
import javax.jms.Session;


/**
 * Created by Administrator on 2016/12/2.
 */
@Component
public class SendJMSMessage {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public SendJMSMessage(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void sendMessageTest(String message) throws JMSException {
        System.out.println("I send message");
        jmsTemplate.convertAndSend("someQueue", message);
    }

}