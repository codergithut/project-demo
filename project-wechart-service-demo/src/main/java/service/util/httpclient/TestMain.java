package service.util.httpclient;

/**
 * Created by Administrator on 2017/2/22.
 */

import java.util.HashMap;
import java.util.Map;

//对接口进行测试
public class TestMain {
//    private String url = "https://192.168.1.101/";
    private String charset = "utf-8";
    private HttpClientUtil httpClientUtil = null;

    public TestMain(){
        httpClientUtil = new HttpClientUtil();
    }

    public void test(){
        String httpOrgCreateTest = "http://tianjian3209.vicp.io/images/pic.png";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("word","test");
//        createMap.put("authpass","*****");
//        createMap.put("orgkey","****");
//        createMap.put("orgname","****");
        String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
        System.out.println("result:"+httpOrgCreateTestRtn);
    }

    public static void main(String[] args){
        TestMain main = new TestMain();
        main.test();
    }
}