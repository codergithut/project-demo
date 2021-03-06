package webSource.mybatis.configuration;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import webSource.mybatis.routingdata.CustomerContextHolder;
import webSource.mybatis.routingdata.DynamicDataSource;
import webSource.util.AESUtil;
import webSource.util.Security;
import webSource.util.plugin.SecurityInterceptor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2016/12/30
 * @description
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource DataSource() {
        System.out.println("-------------------- firstDataSource init ---------------------");
        DataSource dataSource=DataSourceBuilder.create().build();
        return dataSource;
    }
    /**
     * 可以通过这个类,详细配置mybatis
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        AbstractRoutingDataSource dataSource = initRoutingDataSource();
        bean.setDataSource(dataSource);
        //模型对象会默认在这个路径下
        bean.setTypeAliasesPackage("webSource.jpa.entry");
        SecurityInterceptor securityInterceptor = new SecurityInterceptor();
        securityInterceptor.setSecurity(getSecurity());
        bean.setPlugins(new Interceptor[]{securityInterceptor});
        //分页插件,插件无非是设置mybatis的拦截器
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
        //添加插件
//        bean.setPlugins(new Interceptor[]{pageHelper});


        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //设置xml扫描路径
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("sqlSessionFactory init fail",e);
        }
    }

    /**
     * 数据源路由类
     * @return
     */
    @Bean
    public AbstractRoutingDataSource initRoutingDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> dataSources = new HashMap<Object,Object>();
        dataSources.put("dataSource", DataSource());
        dynamicDataSource.setTargetDataSources(dataSources);
        dynamicDataSource.setDefaultTargetDataSource(DataSource());
        return dynamicDataSource;
    }



    @Bean
    public Security getSecurity() {
        return new AESUtil();
    }



    /**
     * 用于实际查询的sql工具,传统dao开发形式可以使用这个,基于mapper代理则不需要注入
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 事务管理,具体使用在service层加入@Transactional注解
     */
    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(DataSource());
    }
}