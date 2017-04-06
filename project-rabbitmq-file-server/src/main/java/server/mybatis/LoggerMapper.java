package server.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import server.entity.Logger;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2016/12/30
 * @description 日志消息调用数据库mybatis接口
 */
@Mapper
public interface LoggerMapper {
    Logger findUserByName(@Param("bizmsgid") String bizmsgid);

    boolean insertUser(Logger logger);
}
