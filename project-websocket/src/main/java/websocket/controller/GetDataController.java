package websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import websocket.entity.Friend;
import websocket.entity.LoginInfo;
import websocket.entity.User;
import websocket.service.FriendService;
import websocket.service.LoginInfoService;
import websocket.service.UserService;
import websocket.util.token.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/26
 * @description
 */
@RestController
public class GetDataController {

    @Autowired
    FriendService friendService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/getFriends", method= RequestMethod.POST)
    @ResponseBody
    public Object loginPost(String userid) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        List<Friend> friends = friendService.selectByParam(params);
        return friends;
    }

    @RequestMapping(value = "/getUserInfo", method= RequestMethod.POST)
    @ResponseBody
    public Object getUserInfo(String userid) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        User user = userService.selectById(userid);
        if(user != null) {
            return user;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/getUserInfobak", method= RequestMethod.GET)
    @ResponseBody
    public Object getUserInfobak(String userid) throws Exception {
        userid = "root1@qq.com";
        Map<String,Object> params = new HashMap<String,Object>();
        User user = userService.selectById(userid);
        if(user != null) {
            return user;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/getFriendsbak", method= RequestMethod.GET)
    @ResponseBody
    public Object loginPostbak(String userid) throws Exception {
        userid = "root1@qq.com";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        List<Friend> friends = friendService.selectByParam(params);
        return friends;
    }
}
