package client.service;


import client.entity.Logger;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/31
 * @description 日志事件接口
 */
public interface LoggerService {
    boolean insertLoggerInfo(Logger logger);
}
