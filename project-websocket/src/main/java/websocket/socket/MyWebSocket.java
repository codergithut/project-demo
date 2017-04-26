package websocket.socket;

import org.springframework.stereotype.Component;
import websocket.config.CommonConstant;
import websocket.model.Message;
import websocket.model.TalkMessage;
import websocket.service.LoginInfoService;
import websocket.service.ServiceBean;
import websocket.util.BeanUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/24
 * @description
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {

    LoginInfoService loginInfoService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String userid;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) throws Exception {
        this.session = session;
        ServiceBean serviceBean = BeanUtils.getBean("serviceBean");
        String token = session.getRequestParameterMap().get("token").toString();
        String id = serviceBean.checkTokenAndGetName(token);
        System.out.println(token);
        //加入set中
        //在线数加1
        webSocketSet.add(this);
        addOnlineCount();
        if(id != null){
            userid = id;
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
            try {
                sendMessage(CommonConstant.XIAOXI);
            } catch (IOException e) {
                System.out.println("IO异常");
            }
        } else {
            /**
             * 通过抛出异常触发管理器对容器中的session进行清理
             * 不是很友好
             */
            onClose();
            throw new Exception("this is unsave session");
        }

    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param recMessage 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String recMessage, Session session) throws IOException {
        //todo message需要给我特定的说明比如 添加好友addfriend@+消息体 聊天就是talk@+消息体 群聊天就是allTalk@+消息体  等等
        TalkMessage messageSingle = new TalkMessage();
        this.session = session;
        messageSingle.setFromId(userid);
        messageSingle.setToId(recMessage.split("\\|")[0]);
        messageSingle.setContent(recMessage);
        String json = messageSingle.changeToJSON();
        System.out.println(json);
        Message message = Message.changeToObject(json);
        System.out.println("来自客户端的消息:" + message);
        if(message instanceof TalkMessage) {
            sendUserInfo((TalkMessage)message);
        }


    }

    public void sendUserInfo(TalkMessage message) throws IOException {
        String rec = message.getToId();
        for(MyWebSocket item : webSocketSet) {
            if(item.getUserid() != null && item.getUserid().equals(rec)) {
                item.sendMessage(message.getContent());
            }
        }
    }






    /**
     * 发生错误时调用
     @OnError
     */
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
     this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }


     /**
      * 群发自定义消息
      * */
    public static void sendAllInfo(String message) throws IOException {
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }

}