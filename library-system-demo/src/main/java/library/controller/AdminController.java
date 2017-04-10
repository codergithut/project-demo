package library.controller;


import library.domain.Admin;
import library.server.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/4/3.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    //初始化登录检查页面
    @RequestMapping(value="/check", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    //对登录信息进行验证并返回页面
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String getAdmin(Admin admin) throws IOException, SQLException {
        boolean result = adminService.checkAdminIsOk(admin);
        if(result){
            return "admin/manage";
        }else{
            return "admin/fail";
        }
    }

}
