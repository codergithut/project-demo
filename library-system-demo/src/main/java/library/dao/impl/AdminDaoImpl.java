package library.dao.impl;

import library.dao.AdminDao;
import library.entity.Admin;
import library.mybatis.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public class AdminDaoImpl implements AdminDao {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin findAdminByName(String name) {
        return adminMapper.findAdminByName(name);
    }

    @Override
    public void insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
    }

    @Override
    public void deleteAdminById(String adminid) {
        adminMapper.deleteAdminById(adminid);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);

    }


    @Override
    public Admin findAdminById(int adminid) {
        return adminMapper.findAdminById(adminid);
    }

    @Override
    public List<Admin> findAdminAll() {
        return adminMapper.findAdminAll();
    }
}
