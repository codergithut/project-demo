package paging.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.abel533.mapperhelper.MapperHelper;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by xujiashuai on 2016/6/18.
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    /**
     * 此处不能用springboot自带的方式注解dataSource会出现null的异常
     * 有时间可以看看
     * @return
     */
    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    public DataSource DataSource() {
        System.out.println("-------------------- firstDataSource init ---------------------");
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setPassword("root");
        dataSource1.setUsername("root");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/mysql");
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource1;
    }



    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(DataSource());
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        bean.setTypeAliasesPackage("paging.domain");

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactoryBean());
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(DataSource());
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() throws ClassNotFoundException {
        MapperScannerConfigurer mapperConfig = new MapperScannerConfigurer();
        mapperConfig.setBasePackage("paging.mapper");
        mapperConfig.setMarkerInterface(Class.forName("paging.common.MyMapper"));
        Properties pro = new Properties();
        pro.put("mappers", "tk.mybatis.mapper.common.Mapper");
        pro.put("IDENTITY", "select uuid()");
        pro.put("ORDER", "BEFORE");
        mapperConfig.setProperties(pro);
        return mapperConfig;
    }

    @Bean
    @DependsOn("sqlSessionTemplate")
    public MapperHelper getMapperHelper(SqlSessionTemplate sqlSession) {
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setSqlSessions(new SqlSession[]{sqlSession});
        mapperHelper.initMapper();
        return mapperHelper;
    }
}