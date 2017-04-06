package client.service.impl;

import client.entity.Logger;
import client.mybatis.LoggerMapper;
import client.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/31
 * @description 日志处理实现类
 */
@Service
public class LoggerServiceImpl implements LoggerService {

    @Autowired
    LoggerMapper loggerMapper;


    public boolean insertLoggerInfo(Logger logger) {

        return loggerMapper.insertUser(logger);

    }
}
