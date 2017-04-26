package library.domain;

import javax.persistence.*;

/**
 * Created by w on 2017/3/13.
 */
@Entity
@Table(name="reader")
public class Reader {
    private int readerid;
    private String username;
    private String password;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="readerid")
    public int getReaderid() {return readerid;}

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    @Column(name="username")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {this.username = username;}
    public Reader(int readerid,String username,String password) {this.username = username;this.readerid = readerid;this.password=password;}
    public void setReaderid(int  readerid) {this.readerid=readerid ;}
    public Reader(){} public void setPassword(String password) {this.password = password;}

}
