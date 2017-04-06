package webSource.soap;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21.
 */
@EnableGlobalMethodSecurity(prePostEnabled=false)
public class HelloServiceImpl implements HelloService {
    private Map<Long, Hello> userMap = new HashMap<Long, Hello>();
    public HelloServiceImpl() {
        Hello user = new Hello();
        user.setUserId(10001L);
        user.setUsername("liyd1");
        user.setEmail("liyd1@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
        user = new Hello();
        user.setUserId(10002L);
        user.setUsername("liyd2");
        user.setEmail("liyd2@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
        user = new Hello();
        user.setUserId(10003L);
        user.setUsername("liyd3");
        user.setEmail("liyd3@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
    }
    @Override
    public String getName(Long userId) {
        return "liyd-" + userId;
    }
    @Override
    public Hello getUser(Long userId) {
        return userMap.get(userId);
    }
}