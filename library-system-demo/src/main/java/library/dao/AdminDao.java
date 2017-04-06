package library.dao;

import library.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public interface AdminDao {
    //根据用户名获取用户信息
    Admin findAdminByName(@Param("name") String name);

    //将用户信息插入到数据库
    void insertAdmin(Admin admin);

    //按照用户id删除用户信息
    void deleteAdminById(@Param("adminid") String adminid);

    //更新用户信息
    void updateAdmin(Admin admin);

    //根据用户id获取用户信息
    Admin findAdminById(@Param("adminid") int adminid);

    //查询所有用户信息
    List<Admin> findAdminAll();

}
