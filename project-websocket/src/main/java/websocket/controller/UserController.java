package websocket.controller;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import websocket.entity.LoginInfo;
import websocket.entity.User;
import websocket.service.FriendService;
import websocket.service.LoginInfoService;
import websocket.service.UserService;
import websocket.util.token.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xujiashuai on 2016/6/18.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;

    @Autowired
    LoginInfoService loginInfoService;



    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String loginGet() throws Exception {
        return "login";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public ModelAndView loginPost(User user, HttpServletRequest request) throws Exception {
        User userGET= userService.slectById(user.getId());
        if(userGET == null || !userGET.equals(user)) {
            return new ModelAndView("5xx");
        }
        Map<String,Object> token = new HashMap<String,Object>();
        token.put("userinfo", user);
        String tokenResult = SecurityUtil.authentication(token);
        Map<String,String> model = new HashMap<String,String>();
        model.put("token", tokenResult);
        user.setStatus("上线");
        model.put("userid", user.getId());

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setIp(request.getRemoteHost());
        loginInfo.setLoginway("PC");
        loginInfo.setSignintime(new Date());
        loginInfo.setToken(tokenResult);
        loginInfo.setUserid(userGET.getId());
        loginInfoService.save(loginInfo);
        userService.updateUser(user);
        return new ModelAndView("client2", model);
    }


}

