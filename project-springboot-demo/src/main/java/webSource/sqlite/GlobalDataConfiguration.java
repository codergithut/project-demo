package webSource.sqlite;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/1/18
 * @description
 */
@Configuration
public class GlobalDataConfiguration {
    @Bean(name="SecondaryDataSource")
    @ConfigurationProperties(prefix="datasource.secondary")
    public DataSource secondaryDataSource() {
        System.out.println("-------------------- SecondaryDataSource init ---------------------");
        DataSource dataSource=DataSourceBuilder.create().build();
        return dataSource;
    }
}