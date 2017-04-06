package webSource.controller.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


/**
 * Created by Administrator on 2016/11/29.
 *
 * 应用freemarker需要pom文件引入对应的boot-start-freemarker包
 * 配置文件中添加属性样式如下
 * spring.freemarker.template-loader-path[0]=classpath:/templates/error/
 * spring.freemarker.template-loader-path[1]=classpath:/templates/service/
 * ect
 * 视图路径就是自动扫描路径加配置路径加返回的字符串的值后缀会自动识别
 *测试方法入口  http://localhost:8080/rest/1
 */

//   http://localhost:8080/freemarker

//Controller 是配置视图层的入口
@Controller
@RequestMapping(value="/freemarker")
public class MyTemplateController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String getUser(Map<String,Object> model) {
        model.put("name","tianjian");
        model.put("password","tianjian");
        return "template";
    }

    @RequestMapping(value="websocket", method= RequestMethod.GET)
    public String webSocket(Map<String,Object> model) {
        return "websocket";
    }

}