package webSource.rabbit.rpc.Client;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/15
 * @description
 */
public class Client {
    private Connection connection;
    private Channel channel;
    private String replyQueueName;



    private QueueingConsumer consumer;
    public Client init() throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setPassword("guest");
        factory.setUsername("guest");
        connection=factory.newConnection();
        channel=connection.createChannel();
        return this;
    }

    public Client setupConsumer() throws IOException {
        replyQueueName =channel.queueDeclare().getQueue();
        consumer=new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName,false,consumer);
        return this;
    }


    public String call(String message) throws IOException, InterruptedException {
        String response=null;
        channel.basicPublish("rpc","ping",getRequestProperties(),message.getBytes());
        System.out.println("Sent 'ping' RPC call. Waiting for reply...");
        while(true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            response=new String(delivery.getBody(),"UTF-8");
            break;
        }
        return response;
    }

    public void close() throws IOException {
        connection.close();
    }

    private BasicProperties getRequestProperties(){
        return new BasicProperties.Builder().replyTo(replyQueueName).build();
    }

    public static String createRequest(){
        float epoch=System.currentTimeMillis()/1000;
        JSONStringer msg=new JSONStringer();
        return msg.object().key("client__name").value("RPC Client 1.0").key("time").value(Float.toHexString(epoch)).endObject().toString();
    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        String response=null;
        Client client=null;
        client=new Client();
        client.init().setupConsumer();
        response=client.call(Client.createRequest());
        System.out.println("PRC Reply ---"+response);
        client.close();
    }
}
