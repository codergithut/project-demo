package library.mapper;

import library.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xujiashuai on 2016/6/18.
 */
@Mapper
public interface UserMapper {
    public User selectById(@Param("id") String id);
    public List<User> list();
}
