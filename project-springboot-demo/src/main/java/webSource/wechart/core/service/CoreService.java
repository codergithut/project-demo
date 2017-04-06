package webSource.wechart.core.service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/21
 * @description
 */
public interface CoreService {
    public  String processRequest(HttpServletRequest request) throws IOException;
}
