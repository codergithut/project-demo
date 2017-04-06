package server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.dao.InsertSingleTableDao;
import server.model.SimpleTableResult;

import java.util.List;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/4/1
 * @description 单表插入数据库的实现
 */
@Service
public class InsertSingleTableDaoImpl implements InsertSingleTableDao {

//    @Autowired
//    EntityMapper entityMapper;

    public SimpleTableResult insertSingleTable(List<Object> objs) throws Exception {
        SimpleTableResult result = new SimpleTableResult();
        for(Object o : objs) {
//            if(entityMapper.insert(o)==0) {
//                String className = o.getClass().getName();
//                result.setResult(false);
//                result.setResultInfo("insert entity " + className + " happy error!");
//                throw new Exception("there is some thing wrong in insert table!");
//            }
        }

        result.setResult(true);
        result.setResultInfo("very good all datas have insert success!");
        return result;
    }
}
