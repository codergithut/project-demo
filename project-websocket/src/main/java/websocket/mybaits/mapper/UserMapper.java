package websocket.mybaits.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import websocket.entity.User;

import java.util.List;

/**
 * Created by xujiashuai on 2016/6/18.
 * 普通的mapper对象需要有xml文件与之对应
 */
@Mapper
public interface UserMapper {
    public User selectById(@Param("id") String id);
    public List<User> list();
}
