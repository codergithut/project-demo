package websocket.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import websocket.config.CommonConstant;
import websocket.entity.Friend;
import websocket.entity.User;
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
import java.util.ArrayList;
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

    LoginInfoService loginInfoService;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String userid;

    ServiceBean serviceBean ;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) throws Exception {
        this.session = session;
        serviceBean = BeanUtils.getBean("serviceBean");
        String token = session.getRequestParameterMap().get("token").toString();
        String id = serviceBean.checkTokenAndGetName(token);
        System.out.println(token);
        //加入set中
        //在线数加1
        addOnlineCount();
        if(id != null){
            userid = id;
            webSocketSet.add(this);
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
            try {
                List<Friend> friends = serviceBean.getAllFriends(id);
                Map<String,Object> friendsData = new HashMap<String,Object>();
                friendsData.put("type", "friendsinfo");
                friendsData.put("Data", friends);

                Map<String,Object> userData = new HashMap<String,Object>();
                User user = serviceBean.getUserInfo(id);
                userData.put("type", "userinfo");
                userData.put("Data", user);
                sendMessage(JSON.toJSONString(friendsData));
                sendMessage(JSON.toJSONString(userData));
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
        JSONObject jsStr = JSONObject.parseObject(recMessage);
        String type = jsStr.get("type").toString();
        if(type.equals("talk")) {
            sendUserInfo(jsStr);
        }

        if(type.equals("addFriend")) {
            sendAddFriendRequest(jsStr);
        }

        if(type.equals("agreeFriend")) {
            sendResultFrendRequest(jsStr);
        }

        if(type.equals("recFriend")) {

        }


    }

    private void sendResultFrendRequest(JSONObject jsStr) throws IOException {
        String toid = jsStr.get("account").toString();
        String result = jsStr.get("result").toString();
        String id = jsStr.get("id").toString();
        if(result.equals("agree")) {
            serviceBean.insertFriend(id,toid);

            for(MyWebSocket item : webSocketSet) {
                if(item.getUserid() != null && item.getUserid().equals(toid)) {
                    List<Friend> friends = serviceBean.getAllFriends(toid);
                    Map<String,Object> friendsData = new HashMap<String,Object>();
                    friendsData.put("type", "friendsinfo");
                    friendsData.put("Data", friends);
                    item.sendMessage(JSON.toJSONString(friendsData));

                    Map<String,String> send = new HashMap<String,String>();
                    send.put("type", "agree");
                    send.put("friendid", id);
                    item.sendMessage(JSON.toJSONString(send));
                }
            }

            for(MyWebSocket item : webSocketSet) {
                if(item.getUserid() != null && item.getUserid().equals(id)) {
                    List<Friend> friends = serviceBean.getAllFriends(id);
                    Map<String,Object> friendsData = new HashMap<String,Object>();
                    friendsData.put("type", "friendsinfo");
                    friendsData.put("Data", friends);
                    item.sendMessage(JSON.toJSONString(friendsData));
                }
            }
        }
    }

    private void sendAddFriendRequest(JSONObject jsStr) throws IOException {
        String account = jsStr.get("account").toString();
        String id = jsStr.get("id").toString();
        for(MyWebSocket item : webSocketSet) {
            if(item.getUserid() != null && item.getUserid().equals(account)) {
                Map<String,Object> data = new HashMap<String,Object>();
                User user = serviceBean.getUserInfo(id);
                data.put("type", "friendRequest");
                data.put("user",user);
                item.sendMessage(JSON.toJSONString(data));
            }
        }
    }


    public void sendUserInfo(JSONObject jsStr) throws IOException {
        String toid = jsStr.get("otherId").toString();
        for(MyWebSocket item : webSocketSet) {
            if(item.getUserid() != null && item.getUserid().equals(toid)) {
                item.sendMessage(jsStr.toJSONString());
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