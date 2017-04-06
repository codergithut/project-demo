package webSource.jdbc;

/**
 * Created by Administrator on 2016/11/30.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import webSource.jdbc.sql.SqlData;

@Component
public class JdbcTemplateBean implements SqlData{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean testConnection(){
        return jdbcTemplate.queryForObject(TEST_CONNECTION,Boolean.class);
    }


}
