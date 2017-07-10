package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by Administrator on 2016/11/29.
 *
 *@RestController 是rest风格的入口
 *
 * testRepository 通过jpa封装的接口连接数据库
 *
 * jdbcTest 通过jdbc连接数据库
 *
 * sessionFactory 获取hibernate的sessionFactory工厂
 *
 *测试方法入口  http://localhost:8080/rest/1
 */

@RestController
@RequestMapping(value="/rest")
public class RestControllerDemo {

    @Resource
    private MongoTemplate mongoTemplate1;
    @Resource
    private MongoTemplate mongoTemplate2;


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String getUser(@PathVariable Long id) throws IOException, SQLException {

//        PVUV pvuv = new PVUV();
//        pvuv.setDate(LocalDate.now());
//        pvuv.setPv(1000);
//        pvuv.setUv(200);
//        mongoTemplate1.save(pvuv, "pvUvCollection");
//        mongoTemplate2.save(pvuv, "pvUvCollection");

        return "haha";
    }

}