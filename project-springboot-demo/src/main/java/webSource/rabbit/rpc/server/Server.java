package webSource.rabbit.rpc.server;


import com.rabbitmq.client.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/15
 * @description
 */
public class Server {
    private Connection connection;
    private Channel channel;
    private QueueingConsumer consumer;
    public Server Server(){
        return this;
    }

    public Server init() throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        connection=factory.newConnection();
        channel=connection.createChannel();
        channel.exchangeDeclare("rpc","direct");

        channel.queueDeclare("ping",false,false,false,null);
        channel.queueBind("ping","rpc","ping");
        consumer=new QueueingConsumer(channel);
        channel.basicConsume("ping",false,"ping",consumer);
        System.out.println("Waiting for RPC calls...");
        return this;
    }

    public void closeConnection(){
        if(connection!=null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void serveRequests() throws InterruptedException, IOException {
        while(true){
            QueueingConsumer.Delivery deliver=consumer.nextDelivery();
            BasicProperties props=deliver.getProperties();
            channel.basicAck(deliver.getEnvelope().getDeliveryTag(),false);
            System.out.println("Received API call...replying...");
            channel.basicPublish("",props.getReplyTo(),null,getResponse(deliver).getBytes("UTF-8"));
        }
    }

    private String getResponse(QueueingConsumer.Delivery delivery) throws UnsupportedEncodingException {
        String response=null;
        String message=new String(delivery.getBody(),"UTF-8");
        JSONObject jsonObject=new JSONObject(message);
        response="Pong!"+jsonObject.getString("time");
        return response;
    }

    public static void main(String[] args){
        Server server=null;
        server=new Server();
        try {
            server.init().serveRequests();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if(server!=null){
                server.closeConnection();
            }

        }
    }


}
