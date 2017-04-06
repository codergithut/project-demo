package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/28
 * @description springboot项目入口方法
 */
@SpringBootApplication
public class Server {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Server.class, args);
    }
}
