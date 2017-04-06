package webSource.rabbitmq.utils;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/22
 * @description
 */
/**
 * XML验证结果Bean
 * @author Peng Wei
 */
public class XmlValidateResult {

    // 是否通过验证
    private boolean validated;

    // 错误信息
    private String errorMsg;

    // 构造函数，默认为不通过，错误原因为空字符串
    public XmlValidateResult() {
        validated = false;
        errorMsg = "";
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

}