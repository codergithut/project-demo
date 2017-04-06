package webSource.wechart.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/6
 * @description
 */
public class RestHttpClientTest {

    public static void main(String[] args) throws HttpException, IOException {
        HttpClient httpClient = new HttpClient();
        //需要验证
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("guest", "guest");

        httpClient.getState().setCredentials(AuthScope.ANY, creds);


        //设置http头
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);

        GetMethod method = new GetMethod("http://localhost:15672/api/queues");
        method.setDoAuthentication(true);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        try {
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Method failed code="+statusCode+": " + method.getStatusLine());
            } else {
                System.out.println(new String(method.getResponseBody(), "utf-8"));
            }
        } finally {
            method.releaseConnection();
        }
    }
}
