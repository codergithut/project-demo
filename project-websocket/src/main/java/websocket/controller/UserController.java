package websocket.controller;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import websocket.entity.Friend;
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
import java.util.List;
import java.util.Map;

/**
 * Created by xujiashuai on 2016/6/18.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginInfoService loginInfoService;



    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String loginGet() throws Exception {
        return "login1";
    }

    @RequestMapping(value = "/check", method= RequestMethod.POST)
    @ResponseBody
    public Map<String,String> loginPost(String id, String password, HttpServletRequest request) throws Exception {
        Map<String,Object> model = new HashMap<String,Object>();
        Map<String,Object> token = new HashMap<String,Object>();
        Map<String,String> data = new HashMap<String,String>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id", id);
        params.put("password", password);
        User user= userService.selectById(id);
        if(user == null || !user.getPassword().equals(password)) {
            return data;
        }
        token.put("userinfo", user);
        String tokenResult = SecurityUtil.authentication(token);
        model.put("token", tokenResult);
        user.setStatus("上线");
        model.put("userid", user.getUserid());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setIp(request.getRemoteHost());
        loginInfo.setLoginway("PC");
        loginInfo.setSignintime(new Date());
        loginInfo.setToken(tokenResult);
        loginInfo.setUserid(user.getUserid());
        loginInfoService.save(loginInfo);
        userService.updateUser(user);

        data.put("userid",user.getUserid());
        data.put("token", tokenResult);
        System.out.println(tokenResult);
        return data;
    }

    @RequestMapping(value = "/success", method= RequestMethod.GET)
    public ModelAndView success(String token, String userid, HttpServletRequest request) throws Exception {

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("token", token);
        model.put("userid", userid);
        System.out.println(token);
        return new ModelAndView("client3", model);
    }

    @RequestMapping(value = "/fail", method= RequestMethod.POST)
    public ModelAndView fail(User user, HttpServletRequest request) throws Exception {
        return new ModelAndView("client3", null);
    }





}

