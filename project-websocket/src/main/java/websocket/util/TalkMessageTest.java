package websocket.util;

import websocket.model.TalkMessage;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/27
 * @description
 */
public class TalkMessageTest {
    public static void main(String[] args) {
        TalkMessage messageSingle = new TalkMessage();
        messageSingle.setFromId("userid");
        messageSingle.setToId("toid");
        messageSingle.setContent("this is test");
        String json = messageSingle.changeToJSON();

        System.out.println(json);
    }
}
