package library.domain;

import javax.persistence.*;

/**
 * Created by w on 2017/3/14.
 */
@Entity
@Table(name="relation")
public class Relation {

     private int adminid;
     private int bookid;
     private String lendtime;
     private String returntime;
     private int readerid;
     private String fine;


     @Id
     @GeneratedValue(strategy= GenerationType.AUTO)
     @Column(name="adminid")
     public int getAdminid() {return adminid;}
     public void setAdminid(int adminid) {
          this.adminid = adminid;
     }
     @Column(name="bookid")
     public int getBookid() {
          return bookid;
     }
     public void setBookid(int bookid) {
          this.bookid = bookid;
     }

     @Column(name="lendtime")
     public String getLendtime() {return lendtime;}
     public void setLendtime(String lendtime) {
          this.lendtime = lendtime;
     }

     @Column(name="readerid")
     public int getReaderid() {
          return readerid;
     }
     public void setReaderid(int readerid) {
          this.readerid = readerid;
     }

     @Column(name="returntime")
     public String getReturntime() {
          return returntime;
     }
     public void setReturntime(String returntime) {
          this.returntime = returntime;
     }

     @Column(name="fine")
     public String getFine() {return fine;}
     public void setFine(String fine) {this.fine = fine;}
     public Relation(){};
     public Relation(int adminid,int bookid,int readerid,String lendtime,String returntime ,String fine) {
     this.adminid=adminid;this.bookid=bookid;this.readerid=readerid;this.lendtime=lendtime;this.returntime=returntime;this.fine=fine;
     }
}
