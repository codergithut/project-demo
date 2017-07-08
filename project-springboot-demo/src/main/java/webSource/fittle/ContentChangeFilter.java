package webSource.fittle;

import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/7/7
 * @description
 */
@Service
@WebFilter(filterName = "contentChangeFilter")
public class ContentChangeFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException, IOException {
        CoverResponse cr = new CoverResponse((HttpServletResponse)response);
        fc.doFilter(request, cr);
        //处理替换
        String content = cr.getContent();
        //todo 根据用户获取后台权限表信息
        List<String> autosInfo = getAutoURLInfo("user1");
        List<String> allInfos = getAllURLInfo();


        /**
         * 将权限表信息关键字按照需要替换成none 和 block 以实现页面的样式控制
         */
        for(String s : allInfos) {
            if(autosInfo != null && autosInfo.contains(s)) {
                content = content.replace(s, "none");
            } else {
                content = content.replace(s,"block");
            }
        }
        response.getWriter().print(content);
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     *获取当前用户可以用的url列表
     */
    private List<String> getAutoURLInfo(String urerinfo) {
        List<String> userinfo = new ArrayList<String>();
        userinfo.add("url1");
        return userinfo;
    }

    /**
     * 获取所有用户信息列表
     */
    private List<String> getAllURLInfo() {
        List<String> allUrls = new ArrayList<String>();
        allUrls.add("url1");
        allUrls.add("url2");
        return allUrls;
    }
}
