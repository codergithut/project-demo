package webSource.jpa.entry;

import webSource.annotation.encrypt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zhangyu2 on 2016/11/25.
 * 个人建议添加个默认构造方法和带全参数的构造方法可以方便jdbc封装
 */
@Entity
@Table(name="users")
public class User {

    private Long userid ;

    @encrypt
    private String name;

    @encrypt
    private String password ;

    private long group_id;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userid")
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Column(name="group_id")
    public Long getGroup_id() {return group_id;}
    public void setGroup_id(long group_id) {this.group_id = group_id;}

    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="password")
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public User(Long userid,String name,String password){
        this.userid = userid;
        this.name = name;
        this.password = password;
    }

    public User(){
        
    }
}
