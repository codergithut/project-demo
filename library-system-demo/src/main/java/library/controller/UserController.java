package library.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import library.common.MyMapper;
import library.domain.User;
import library.mapper.UserMapper;
import library.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

