package webSource.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/6.
 */
@Controller
public class SecurityController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/security")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
