package websocket.model;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/25
 * @description
 */
public class test {
    public static void main(String[] args) {
        TalkMessage messageSingle1 = new TalkMessage();
        messageSingle1.setFromId("root@qq.com");
        messageSingle1.setToId("root1@qq.com");
        messageSingle1.setContent("this is test");
        String json = messageSingle1.changeToJSON();
        System.out.println(json);


    }
}
