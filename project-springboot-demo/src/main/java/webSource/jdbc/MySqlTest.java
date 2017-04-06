package webSource.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;


/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/1/18
 * @description
 * 测试数据源工作 最基本的用DataSource操作数据库
 * 20170207
 */
@Service
public class MySqlTest {

    @Autowired
    DataSource mysqlDataSource;

    public Object testSqlLite(){
        try {
            System.out.println("开始获取数据源并执行查询操作！！");
            ResultSet obj=mysqlDataSource.getConnection().prepareStatement("select * from users").executeQuery();
            List data=resultSetToList(obj);
            System.out.println("控制台打印数据获取的数据！！");
            for(Object o:data){
                System.out.println(o.toString());
            }
            return data;
        } catch (SQLException e) {
            System.out.println("数据库查询失败请查看日志！！");
            e.printStackTrace();
            return null;
        }

    }

    private List resultSetToList(ResultSet rs) throws SQLException {
        if (rs == null)
            return Collections.EMPTY_LIST;
        ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
        List list = new ArrayList();
        Map rowData = new HashMap();
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

}
