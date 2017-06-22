package webSource.mybatis.routingdata;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/22
 * @description
 */
@Service
public class CustomerContextHolder {
    public static final String DATA_SOURCE_A = "dataSource";
    public static final String DATA_SOURCE_B = "dataSource2";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }
    public static String getCustomerType() {
        return contextHolder.get();
    }
    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
