package websocket.mybaits;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
     * 数据源配置不能通过springboot自带的方法进行处理
     * 有时间可以看看
     * @return 数据源
     */
    @Primary
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    public DataSource DataSource() {
        System.out.println("-------------------- firstDataSource init ---------------------");
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setPassword("php@2016");
        dataSource1.setUsername("root");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/chat");
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource1;
    }


    /**
     *mybatis sqlSessionFactoryBean 配置 用于配置插件扫描的包名添加插件等等
     * @return sqlSessionFactory mybatis配置
     */
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
        bean.setTypeAliasesPackage("websocket.entity");

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


    /**
     * sqlSessionTemplate 模板配置
     * @return
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactoryBean());
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(DataSource());
    }

    /**
     *
     * @return 通用接口配置配置通用接口服务
     * @throws ClassNotFoundException
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() throws ClassNotFoundException {
        MapperScannerConfigurer mapperConfig = new MapperScannerConfigurer();
        mapperConfig.setBasePackage("websocket.mybaits.mapper");
        Properties pro = new Properties();
        //注册通用接口，插件的通用接口是第一个，自定义通用接口是第二个。
        pro.put("mappers", "tk.mybatis.mapper.common.Mapper");
        //定义主键生成策略
        pro.put("IDENTITY", "select uuid()");
        //数据库生成主键需要回塞到对象中
        pro.put("ORDER", "BEFORE");
        mapperConfig.setProperties(pro);
        return mapperConfig;
    }

}