package server.dao;

import org.springframework.stereotype.Service;
import server.model.SimpleTableResult;

import java.util.List;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/4/1
 * @description 单表插入数据库
 */
@Service
public interface InsertSingleTableDao {
    SimpleTableResult insertSingleTable(List<Object> objs) throws Exception;
}
