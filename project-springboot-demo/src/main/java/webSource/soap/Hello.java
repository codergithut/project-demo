package webSource.soap;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/21.
 */
public class Hello implements Serializable {
    private static final long serialVersionUID = -5939599230753662529L;
    private Long              userId;
    private String            username;
    private String            email;
    private Date gmtCreate;

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
