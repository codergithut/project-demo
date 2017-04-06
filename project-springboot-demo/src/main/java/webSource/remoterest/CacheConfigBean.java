package webSource.remoterest;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/12/1.
 */
@Configuration
public class CacheConfigBean {
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return (cacheManager)->{
            cacheManager.setCacheNames(Arrays.asList("user1", "user2"));
        };
    }
}
