package server.model;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 服务端发送消息封装
 */
public class MessageServer {

    private String path;
    private boolean result;
    private String backInfo;
    private String serverName;

    public MessageServer() {
    }

    public String getPath() {
        return path;
    }

    public boolean isResult() {
        return result;
    }

    public String getBackInfo() {
        return backInfo;
    }

    public String getServerName() {
        return serverName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setBackInfo(String backInfo) {
        this.backInfo = backInfo;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
