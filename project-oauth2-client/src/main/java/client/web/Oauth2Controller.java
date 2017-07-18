package client.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/7/18
 * @description
 */
@RestController
public class Oauth2Controller {
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
