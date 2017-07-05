package webSource.thread;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/30
 * @description
 */
@Configurable
@Component
public class ThreadPool{
    @Bean
    @Primary
    public ThreadPoolTaskExecutor getThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(3);
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        threadPoolTaskExecutor.setMaxPoolSize(5);
        threadPoolTaskExecutor.setQueueCapacity(20);
        return new ThreadPoolTaskExecutor();
    }


    @Bean
    public CommonBean getCommonBean() {
        return new CommonBean();
    }

    @Bean
    public PrototypeBean getPrototypeBean() {
        return new PrototypeBean();
    }

}
