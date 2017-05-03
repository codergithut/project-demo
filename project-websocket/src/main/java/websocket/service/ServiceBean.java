package websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import websocket.entity.Friend;
import websocket.entity.LoginInfo;
import websocket.entity.User;
import websocket.model.GetFriendsMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/26
 * @description
 */
@Service
public class ServiceBean {

    @Autowired
    FriendService friendService;

    @Autowired
    LoginInfoService loginInfoService;

    @Autowired
    UserService userService;

    public String checkTokenAndGetName(String token) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("token",token.substring(1,token.length() -1));
        List<LoginInfo> loginInfos = loginInfoService.selectByParam(param);
        if(loginInfos != null && loginInfos.size() > 0) {
            return loginInfos.get(0).getUserid();
        } else {
            return null;
        }
    }

    public List<Friend> getAllFriends(String userid) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userid", userid);
        return friendService.selectByParam(param);
    }

    public User getUserInfo(String userid) {
        return userService.selectById(userid);
    }

    public void insertFriend(String id, String friendid) {
        User friendone = userService.selectById(id);
        User friendtwo = userService.slectById(friendid);
        Friend friendinfo = new Friend();
        friendinfo.setUserid(id);
        friendinfo.setFriendname(friendtwo.getName());
        friendinfo.setFriend(friendid);
        friendinfo.setAddress(friendtwo.getAddress());
        friendinfo.setImage(friendtwo.getImage());
        friendinfo.setRelation("朋友");
        friendinfo.setRemark("默认好友");
        friendinfo.setTag("测试");

        Friend friendinfo1 = new Friend();
        friendinfo1.setUserid(friendid);
        friendinfo1.setFriendname(friendone.getName());
        friendinfo1.setFriend(id);
        friendinfo1.setAddress(friendone.getAddress());
        friendinfo1.setImage(friendone.getImage());
        friendinfo1.setRelation("朋友");
        friendinfo1.setRemark("默认好友");
        friendinfo1.setTag("测试");
        friendService.save(friendinfo);
        friendService.save(friendinfo1);
    }
}
