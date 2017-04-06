package webSource.remoterest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import webSource.jpa.entry.User;

/**
 * Created by Administrator on 2016/12/2.
 */
@Configuration
public class RestBean {

    private final RestTemplate restTemplate;

    @Autowired
    public RestBean(ProxyCustomizer proxyCustomizer,RestTemplateBuilder restTemplateBuilder) {
//        restTemplateBuilder.additionalCustomizers(proxyCustomizer.getProxyCustomizer());
        this.restTemplate = restTemplateBuilder.build();
    }

    public User someRestCall(long id) {
        return this.restTemplate.getForObject("http://localhost:8081", User.class, id);
    }
}
