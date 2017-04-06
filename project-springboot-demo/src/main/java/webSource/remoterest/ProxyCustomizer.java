package webSource.remoterest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

/**
 * Created by Administrator on 2016/12/2.
 */
@Component
public class ProxyCustomizer{
//    @Bean
//    public RestTemplateCustomizer getProxyCustomizer(){
//        return (RestTemplate restTemplate)->{
//                HttpHost proxy = new HttpHost("localhost");
//                HttpClient httpClient = HttpClientBuilder.create()
//                        .setRoutePlanner(new DefaultProxyRoutePlanner(proxy) {
//                            @Override
//                            public HttpHost determineProxy(HttpHost target,
//                                                           HttpRequest request, HttpContext context)
//                                    throws HttpException {
//                                if (target.getHostName().equals("localhost")) {
//                                    return null;
//                                }
//                                return super.determineProxy(target, request, context);
//                            }
//                        }).build();
//                restTemplate.setRequestFactory(
//                        new HttpComponentsClientHttpRequestFactory(httpClient));
//            };
//        return null;
//    }

}
