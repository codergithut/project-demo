package websocket.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import websocket.entity.User;
import websocket.util.ExampleUtil;

import java.util.List;
import java.util.Map;

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

   public User selectById(String id) {
       return super.slectById(id);
   }

   public int updateUser(User user) {
       return super.update(user);
   }

   public List<User> selectByParam(Map<String,Object> params) {
       ExampleUtil example = new ExampleUtil(User.class, params);
       return super.getEntityByParam(example);
   }
}
