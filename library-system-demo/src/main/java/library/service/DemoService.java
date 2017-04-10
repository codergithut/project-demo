package library.service;

import library.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by tianjian on 2017/4/8.
 */
@Service
public class DemoService extends BaseService<User> {
    public int save(User user) {
        if (user == null) {
            throw new NullPointerException("保存的对象不能为空!");
        }
        return super.save(user);
    }

}
