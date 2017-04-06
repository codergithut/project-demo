package webSource.sqlite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/1/18
 * @description
 */
@Service
public class SqlLiteTest {

    @Autowired
    @Qualifier("SecondaryDataSource")
    DataSource sqlitedataSource;

    public void testSqlLite(){
        try {
            Object obj=sqlitedataSource.getConnection().prepareStatement("select * from t1");
            System.out.println(obj.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
