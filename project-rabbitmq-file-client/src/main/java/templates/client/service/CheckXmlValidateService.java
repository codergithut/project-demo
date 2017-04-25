package templates.client.service;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/31
 * @description 检测xml文件是否合法
 */
public interface CheckXmlValidateService {
    boolean CheckXmlFile(String fileString, String serverNumber);
}
