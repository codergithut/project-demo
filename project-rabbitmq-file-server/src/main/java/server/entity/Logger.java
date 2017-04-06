package server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianjain on 2016/11/25.
 * logger表记录的消息处理信息
 */
@Entity
@Table(name="logger")
public class Logger {
    private String bizmsgid;
    private String result;
    private String responsecode ;


    @Id
    @Column(name="bizmsgid")
    public String getBizmsgid() {
        return bizmsgid;
    }

    @Column(name="result")
    public String getResult() {
        return result;
    }

    @Column(name="responsecode")
    public String getResponsecode() {
        return responsecode;
    }

    public void setBizmsgid(String bizmsgid) {
        this.bizmsgid = bizmsgid;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public Logger(){
        
    }
}
