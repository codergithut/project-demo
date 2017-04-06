package webSource.jdbc.sql;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface HqlData {
    String SQL_SERVER="select user from User user where user.id = ?";

    String SQL_USER="select user from User user where user.name = ?";
}
