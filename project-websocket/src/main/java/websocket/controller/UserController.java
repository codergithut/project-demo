package websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import websocket.entity.User;
import websocket.service.UserService;
import websocket.util.token.SecurityUtil;

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



    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String loginGet() throws Exception {
        return "login";
    }

    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public ModelAndView loginPost(User user) throws Exception {
        User userGET= userService.slectById(user.getId());
        if(userGET == null || !userGET.equals(user)) {
            return new ModelAndView("5xx");
        }
        Map<String,Object> sign = new HashMap<String,Object>();
        sign.put("userinfo", user);
        String signResult = SecurityUtil.authentication(sign);
        Map<String,String> model = new HashMap<String,String>();
        model.put("sign", signResult);
        user.setDate(new Date());
        user.setSign(signResult);
        user.setStatus("上线");
        userService.updateUser(user);
        return new ModelAndView("client", model);
    }


}

