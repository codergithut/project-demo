package webSource.mybatis.routingdata;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/22
 * @description
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return CustomerContextHolder.getCustomerType();
    }
}