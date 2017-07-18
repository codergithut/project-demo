package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/7/18
 * @description
 */
@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class);
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
