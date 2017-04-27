package websocket.model;

import com.alibaba.fastjson.JSON;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/25
 * @description
 */
public abstract class Message {

    public String type;

    public Message(String type) {
        this.type = type;
    }

    public String changeToJSON() {
        return JSON.toJSONString(this);
    }

    public static TalkMessage changeToTalkMessage(String json) {
        return JSON.parseObject(json, TalkMessage.class);
    }

}
