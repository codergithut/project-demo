package websocket.mybaits.mapper;

import tk.mybatis.mapper.common.Mapper;
import websocket.entity.User;

/**
 * Created by tianjian on 2017/4/8.
 * 此处必须强调我们通用mapper都是需要对应的实例化的这边就是给Mapper<User>进行通用实例化处理，
 * 如果没有这个接口我们UserService 自动注入Mapper<User>会出现通用mapper对象不能实例化的错误，
 * 本人感觉有点模板方法的意思是不是可以考虑集成到工具中呢
 */
public interface UserCommonMapper extends Mapper<User> {
}
