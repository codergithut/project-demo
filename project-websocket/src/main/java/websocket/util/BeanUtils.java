package websocket.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;
import websocket.service.UserService;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/17
 * @description
 */
@Service
public class BeanUtils implements BeanFactoryAware {
    // Spring的bean工厂
    private static BeanFactory beanFactory;

    public void setBeanFactory(BeanFactory factory) throws BeansException {
        beanFactory = factory;
    }
    public static<T> T getBean(String name){
        return (T)beanFactory.getBean(name);
    }
}