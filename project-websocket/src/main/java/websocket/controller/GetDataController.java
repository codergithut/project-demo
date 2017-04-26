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

    @RequestMapping(value = "/getFriends", method= RequestMethod.GET)
    @ResponseBody
    public Object loginPost(String userid) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        List<Friend> friends = friendService.selectByParam(params);
        model.put("friends", friends);
        return friends;
    }
}
