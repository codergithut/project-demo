package library.entity;

import javax.persistence.*;

/**
 * Created by w on 2017/3/13.
 */
@Entity
@Table(name="book")
public class Book {
    private String bookname;
    private int bookid;
    private String classification;
    private String statement;
    private int number;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="bookid")
    public int getBookid() {
     return bookid;
    }

    @Column(name="bookname")
    public String getBookname() {
        return bookname;
    }



    @Column(name="classification")
    public String getClassification() {
        return classification;
    }

    @Column(name="number")
    public int getNumber() {
        return number;
    }


    @Column(name="statement")
    public String getStatement() {
        return statement;
    }

    public void setBookid(int bookid) {this.bookid = bookid;}
    public void setBookname(String bookname) {this.bookname = bookname;}
    public void setClassification(String classification) {
        this.classification = classification;
    }
    public void setStatement(String statement) {
        this.statement = statement;
    }
    public Book(int bookid,String bookname,String statement,String classification,int number)
    {this.bookname = bookname;this.bookid=bookid;this.statement=statement;this.classification=classification;this.number=number;}
    public Book(){}
    public void setNumber(int number) {this.number = number;}
}
