package websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import websocket.entity.Friend;
import websocket.entity.LoginInfo;
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

    public GetFriendsMessage getAllFriends(GetFriendsMessage friendsInfo) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userid", friendsInfo.getUserid());
        friendsInfo.setFriends(friendService.selectByParam(param));
        return friendsInfo;
    }

}
