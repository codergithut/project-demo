package websocket.token;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/24
 * @description
 */
public class test {
    public static void main(String[] args) throws Exception {
        Map<String,Object> tokens = new HashMap<String,Object>();
        tokens.put("key","test");
        System.out.println(SecurityUtil.authentication(tokens));
    }
}
