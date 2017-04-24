package websocket.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import websocket.entity.User;
import websocket.service.UserService;
import websocket.token.SecurityUtil;

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

    @RequestMapping(value = "/checkUser", method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView checkUserInfo(
//            User user
    ) throws Exception {
        User user = new User();
        user.setId("1");
        user.setName("1");
        user.setPassword("1");
        user.setDate(new Date());
        if(! userService.selectById(user.getId()).equals(user)) {
            return new ModelAndView("5xx");
        }

        Map<String,Object> sign = new HashMap<String,Object>();
        sign.put("userinfo", user);
        String signResult = SecurityUtil.authentication(sign);
        Map<String,String> model = new HashMap<String,String>();
        model.put("sign", signResult);
        return new ModelAndView("client", model);
    }

}

