package webSource.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import webSource.annotation.getTime;
import webSource.jpa.entry.User;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2016/12/30
 * @description
 */
@Mapper
public interface UserMapper {
    @Select("select * from users where name = #{name}")
    User findUserByNameByAnnotation(@Param("name") String name);

    @getTime
    User findUserByName(@Param("name") String name);


    void insertUser(User user);

    void insertUserByGenerate(User user);

    void insertUserByRandom(User user);
}
