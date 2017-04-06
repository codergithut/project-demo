package server.model;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 文件消息
 */
public class FileMessage {
    String fileName;
    byte[] content;
    String type;
    String encode;
    String path;
    String bizMsgId;

    public FileMessage(String fileName, String type) {
        this.fileName=fileName;
        this.content=content;
        this.type=type;
    }

    public FileMessage(){

    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getEncode() {

        return encode;
    }

    public String getBizMsgId() {
        return bizMsgId;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setBizMsgId(String bizMsgId) {
        this.bizMsgId = bizMsgId;
    }
}
