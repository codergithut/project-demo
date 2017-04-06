package server.model;


import java.util.List;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 客户端发送消息封装
 */
public class MessageClient {
    private String serverName;
    private String status;
    private String form;
    private String queueName;
    private List<FileMessage> fileMessages;

    //JSON工具需要
    public MessageClient() {
    }

    public List<FileMessage> getMessageContents() {
        return fileMessages;
    }

    public String getQueueName() {
        return queueName;
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

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
