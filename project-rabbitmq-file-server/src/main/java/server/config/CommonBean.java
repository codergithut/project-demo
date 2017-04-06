package server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.service.impl.ObjectServiceImpl;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/28
 * @description 通用bean注入
 */
@Configuration
public class CommonBean {

    @Bean
    public ObjectServiceImpl getObjectService() {
        return new ObjectServiceImpl();
    }

}
