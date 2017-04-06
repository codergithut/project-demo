package webSource.rabbitmq.model;


import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/13
 * @description
 */
public class Message {
    String serverName;
    String status;
    String form;
    List<FileMessage> fileMessages;

    //JSON工具需要
    public Message() {
    }

    public List<FileMessage> getMessageContents() {
        return fileMessages;
    }

    public String getServerName() {
        return serverName;
    }

    public String getStatus() {
        return status;
    }

    public String getForm() {
        return form;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setMessageContents(List<FileMessage> fileMessages) {
        this.fileMessages = fileMessages;
    }
}
