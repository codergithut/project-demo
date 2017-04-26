package websocket.service;

import org.springframework.stereotype.Service;
import websocket.entity.Friend;
import websocket.entity.LoginInfo;
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
public class LoginInfoService extends BaseService<LoginInfo> {
   public int save(LoginInfo logininfo) {
       if (logininfo == null) {
           throw new NullPointerException("保存的对象不能为空!");
       }
       return super.save(logininfo);
   }

   public LoginInfo selectById(String id) {
       return super.slectById(id);
   }

   public int updateUser(LoginInfo loginInfo) {
       return super.update(loginInfo);
   }

   public List<LoginInfo> selectByParam(Map<String,Object> params) {
       ExampleUtil example = new ExampleUtil(LoginInfo.class, params);
       return super.getEntityByParam(example);
   }
}
