package paging.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import paging.common.MyMapper;
import paging.domain.User;
import paging.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import paging.service.BaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xujiashuai on 2016/6/18.
 */
@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BaseService<User> DemoService;

    @Autowired
    MyMapper mapper;

    @RequestMapping(value = "/queryAll", method= RequestMethod.GET)
    @ResponseBody
    public Map<String,PageInfo> query(Integer page, Integer limit) {
        if(page!= null && limit!= null){
            PageHelper.startPage(page, limit);
        }

        Map<String,PageInfo> pages = new HashMap<String,PageInfo>();
        List<User> users = userMapper.list();

        Map<String,Object> datas= new HashMap<String,Object>();

        PageInfo pageinfo = new PageInfo(users);

        pages.put("page",pageinfo);

        return pages;
    }

    @RequestMapping("/query")
    public String queryDefault() {
        return "index";
    }

    @RequestMapping("/entity")
    public String entityMapper() {
        User user = new User();
        user.setName("OOXX");
        DemoService.save(user);

        User user1 = new User();
        user1.setName("OOXX1");
        mapper.insert(user1);

        System.out.println(userMapper.selectById(user.getId()).getName() + "sssssssssss");

        return "index";
    }
}

