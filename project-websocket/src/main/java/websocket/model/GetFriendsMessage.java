package websocket.model;
import websocket.entity.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/26
 * @description
 */
public class GetFriendsMessage extends Message{
    private String userid;
    List<Friend> friends = new ArrayList<Friend>();
    public GetFriendsMessage() {
        super("getFriends");
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
