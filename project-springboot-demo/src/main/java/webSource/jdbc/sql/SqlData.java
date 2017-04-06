package webSource.jdbc.sql;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface SqlData {
    String TEST_CONNECTION="select curdate()";

    default boolean testConnection(){
        return true;
    }
}
