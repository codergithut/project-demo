package webSource.invoker;

import com.oracle.webservices.internal.api.message.PropertySet;
import org.elasticsearch.common.inject.name.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

/**
 *远程调用需要不同的服务器间进行通信
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/7/13
 * @description
 */
@Component
public class InvokerConfigBean {
    /**
     * 服务端提供远程服务
     * @return
     */
    @Bean(name="/invokerService")
    public HttpInvokerServiceExporter getHttpInvokerServiceExporter() {
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setServiceInterface(InvokerService.class);
        httpInvokerServiceExporter.setService(new InvokerServiceImpl());
        return httpInvokerServiceExporter;
    }

    /**
     * 客户端使用远程服务
     * @return
     */
    @Bean
    @Named("invokerService")
    public HttpInvokerProxyFactoryBean getHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceUrl("http://localhost:8080/invokerService");
        httpInvokerProxyFactoryBean.setServiceInterface(InvokerService.class);
        return httpInvokerProxyFactoryBean;
    }

    @Bean
    public SimpleUrlHandlerMapping getSimpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        Properties pro = new Properties();
        pro.put("/invokerService","/invokerService");
        simpleUrlHandlerMapping.setMappings(pro);
        return simpleUrlHandlerMapping;
    }

}
