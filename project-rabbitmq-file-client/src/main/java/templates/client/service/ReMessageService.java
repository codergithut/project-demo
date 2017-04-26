package templates.client.service;


import templates.client.model.ResponseMessage;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/31
 * @description 消息处理类
 */
public interface ReMessageService {
    boolean saveInfoByMessage(ResponseMessage responseMessage, String path);
}
