package paging.config;

import com.github.abel533.mapperhelper.MapperHelper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
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

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by xujiashuai on 2016/6/18.
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {


    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource DataSource() {
        System.out.println("-------------------- firstDataSource init ---------------------");
        DataSource dataSource= DataSourceBuilder.create().build();
        return dataSource;
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
//            bean.setConfigLocation(resolver.getResource("classpath:mapper/*.xml"));
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(DataSource());
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperConfig = new MapperScannerConfigurer();
        mapperConfig.setBasePackage("com.isea533.mybatis.mapper,com.isea533.mybatis.mapperhelper,com.github.abel533.mapper.Mapper");
        return mapperConfig;
    }

    @Bean
    @DependsOn("sqlSessionTemplate")
    public MapperHelper getMapperHelper(SqlSessionTemplate sqlSession) {
        System.out.println("ssse");
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setMappers(new String[]{"com.isea533.mybatis.mapperhelper.Mapper", "com.github.abel533.mapper.Mapper"});
        mapperHelper.setSqlSessions(new SqlSession[]{sqlSession});
        mapperHelper.initMapper();
        return mapperHelper;
    }
}