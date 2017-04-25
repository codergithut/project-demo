package websocket.socket;

import org.springframework.stereotype.Component;
import websocket.config.CommonConstant;
import websocket.entity.User;
import websocket.model.Message;
import websocket.model.MessageSingle;
import websocket.service.UserService;
import websocket.util.BeanUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/24
 * @description
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {

    UserService userService;

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
    public void onOpen(Session session) {
        this.session = session;
        if(userService == null) {
            userService = BeanUtils.getBean("userService");
        }
        String token = session.getRequestParameterMap().get("token").toString();
        String id = checkTokenAndGetName(token);
        System.out.println(token);

        if(id != null){
            userid = id;
            webSocketSet.add(this);     //加入set中
            addOnlineCount();           //在线数加1
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
            try {
                sendMessage(CommonConstant.XIAOXI);
            } catch (IOException e) {
                System.out.println("IO异常");
            }
        }

    }

    private String checkTokenAndGetName(String token) {

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("sign",token.substring(1,token.length() -1));
        List<User> users = userService.selectByParam(param);
        if(users != null && users.size() > 0) {
            return users.get(0).getId();
        } else {
            return null;
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
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        MessageSingle messageSingle1 = new MessageSingle();
        messageSingle1.setFromId("root@qq.com");
        messageSingle1.setToId("root1@qq.com");
        messageSingle1.setContent("this is test");
        String json = messageSingle1.changeToJSON();
        System.out.println(json);


        Message message1 = Message.changeToObject(json);
        System.out.println("来自客户端的消息:" + message);

        if(message1 instanceof MessageSingle) {
            String rec = ((MessageSingle) message1).getToId();
            for(MyWebSocket item : webSocketSet) {
                if(item.getUserid().equals(rec)) {
                    item.sendMessage(((MessageSingle) message1).getContent());
                    return;
                }
            }
        }



        //群发消息
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
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
    public static void sendInfo(String message) throws IOException {
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