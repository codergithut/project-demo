package websocket.service;

import org.springframework.stereotype.Service;
import websocket.entity.Friend;
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
public class FriendService extends BaseService<Friend> {
   public int save(Friend friend) {
       if (friend == null) {
           throw new NullPointerException("保存的对象不能为空!");
       }
       return super.save(friend);
   }

   public Friend selectById(String id) {
       return super.slectById(id);
   }

   public int updateUser(Friend friend) {
       return super.update(friend);
   }

   public List<Friend> selectByParam(Map<String,Object> params) {
       ExampleUtil example = new ExampleUtil(Friend.class, params);
       return super.getEntityByParam(example);
   }
}
