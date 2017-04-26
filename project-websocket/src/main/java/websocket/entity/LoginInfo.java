package websocket.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/26
 * @description
 */
@Table(name="logininfo")
public class LoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String userid;
    private String loginway;
    private String ip;
    private Date signuptime;
    private Date signintime;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLoginway() {
        return loginway;
    }

    public void setLoginway(String loginway) {
        this.loginway = loginway;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getSignuptime() {
        return signuptime;
    }

    public void setSignuptime(Date signuptime) {
        this.signuptime = signuptime;
    }

    public Date getSignintime() {
        return signintime;
    }

    public void setSignintime(Date signintime) {
        this.signintime = signintime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
