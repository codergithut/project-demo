package server.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/30
 * @description 生成的标准xml响应报文
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResponseMessage {
    @XmlElement
    private String bizMsgId;

    @XmlElement
    private String successFlag;

    @XmlElement
    private String responseInfo;

    @XmlElement
    private String responseCode;

    public ResponseMessage() {
    }


    public String getBizMsgId() {
        return bizMsgId;
    }

    public String getSuccessFlag() {
        return successFlag;
    }

    public String getResponseInfo() {
        return responseInfo;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setBizMsgId(String bizMsgId) {
        this.bizMsgId = bizMsgId;
    }

    public void setSuccessFlag(String successFlag) {
        this.successFlag = successFlag;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}
