package library.server;

import library.entity.Admin;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public interface AdminService {
    //检查当前用户是否合法
    boolean checkAdminIsOk(Admin admin);
}
