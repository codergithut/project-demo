package websocket.service;

import org.springframework.stereotype.Service;
import websocket.entity.User;

/**
 * Created by tianjian on 2017/4/8.
 */

/**
* @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
* @version 1.0, 2017/4/11
* @description 我们对应单表操作的具体实现有点dao的意思
*/
@Service
public class UserService extends BaseService<User> {
   public int save(User user) {
       if (user == null) {
           throw new NullPointerException("保存的对象不能为空!");
       }
       return super.save(user);
   }

   public User selectById(long id) {
       return super.slectById(id);
   }
}
