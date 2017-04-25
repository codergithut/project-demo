package websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/24
 * @description
 */
@SpringBootApplication
@EnableCaching
public class StartUp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartUp.class, args);
    }
}
