package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/24
 * @description
 */
@SpringBootApplication
public class Application implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8081);
    }
}
