package webSource.rtx.Message;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/1/20
 * @description
 */
public class Message {
    String content;
    String sendName;
    String receiveName;
    String type;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getSendName() {
        return sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public String getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public void setType(String type) {
        this.type = type;
    }
}
