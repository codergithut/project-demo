package library.domain;

import javax.persistence.*;

/**
 * Created by w on 2017/3/13.
 */

@Entity
@Table(name="admin")
public class Admin {

    private String name;
    private String password;
    private int adminid;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="adminid")
    public int getAdminid() {
        return adminid;
    }

    @Column(name="name")
    public String getName() {
        return name;}

    @Column(name="password")
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {this.name = name;}
    public void setAdminid(String id) {this.adminid = adminid;}
    public Admin(int adminid,String name,String password) {this.adminid=adminid;this.name =name;this.password=password;}
    public Admin(){
    }

    @Override
    public boolean equals(Object admin){
        Admin adminNow = null;
        if(admin instanceof Admin) {
            adminNow = (Admin)admin;
            if(adminNow.getName().equals(getName()) && adminNow.getPassword().equals(getPassword())){
                return true;
            }
        }
        return false;
    }
}
