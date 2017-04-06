package webSource.controller.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/12/6.
 */
@Controller
public class DocTemplate {
    @RequestMapping("/doc")
    public String index() {
        return "template";
    }

}