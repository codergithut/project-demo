package library.mapper;

import library.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zlj on 2017/3/27.
 */
@Mapper
public interface AdminMapper {
    Admin findAdminByName(@Param("name") String name);

    void insertAdmin(Admin admin);

    void deleteAdminById(@Param("adminid") String adminid);

    void updateAdmin(Admin admin);

    void selectalladmin(Admin admin);

    Admin findAdminById(@Param("adminid") int adminid);

    List<Admin> findAdminAll();

}
