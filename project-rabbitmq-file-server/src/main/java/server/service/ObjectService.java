package server.service;

import java.util.List;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/4/1
 * @description 将字符串转换为对象
 */
public interface ObjectService {
    List<Object> getObject(String content);
}
