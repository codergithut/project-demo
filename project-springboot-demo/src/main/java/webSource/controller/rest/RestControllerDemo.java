package webSource.controller.rest;

import com.github.abel533.entity.EntityMapper;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import webSource.annotation.getTime;
import webSource.jpa.entry.User;
import webSource.mybatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webSource.jpa.repository.JpaRepositoryBean;
import webSource.mybatis.routingdata.CustomerContextHolder;
import webSource.sqlite.SqlLiteTest;
import webSource.thread.CommonBean;
import webSource.thread.PrototypeBean;
import webSource.tool.GetUrlResource;
import webSource.util.BeanUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    DataSource dataSource;


    @Autowired
    JpaRepositoryBean testRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    SqlLiteTest sqlLiteTest;

//    @Autowired
    PrototypeBean prototypeBean;

//    @Autowired
    CommonBean commonBean;
//
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public List<Object> getUser(@PathVariable Long id) throws IOException, SQLException {



        List<Object> users=new ArrayList<Object>();

        GetUrlResource getResource=new GetUrlResource();

        users.add(getResource.getWealthByUrlResource());

        User user = new User();
        user.setPassword("thisis test");
        user.setName("hshshs");
        user.setGroup_id(33l);
        user.setUserid(2222l);

        commonBean = (CommonBean) BeanUtils.getFileSendService("commonBean");
        prototypeBean = (PrototypeBean)BeanUtils.getFileSendService("prototypeBean");





        CustomerContextHolder.setCustomerType(CustomerContextHolder.DATA_SOURCE_A);
        commonBean.sayCommonBean();
        prototypeBean.sayPrototypeBean();

        commonBean.addNumber();
        prototypeBean.addNumber();

        Thread t = new Thread(commonBean);
//        t.setDaemon(true);
        t.start();



        //new Thread(prototypeBean).run();


        taskExecutor.execute(commonBean);
        taskExecutor.execute(prototypeBean);

       // userMapper.insertUser(user);


        users.add(userMapper.findUserByName("hshshs"));

        users.add(userMapper.findUserByNameByAnnotation("11"));

        return users;
    }

}