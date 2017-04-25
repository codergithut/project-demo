package websocket.model;

import com.alibaba.fastjson.JSON;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/25
 * @description
 */
public abstract class Message {
    public String changeToJSON() {
        return JSON.toJSONString(this);
    }

    public static MessageSingle changeToObject(String json) {
        return JSON.parseObject(json, MessageSingle.class);
    }

}
