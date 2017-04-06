package service.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.entity.MessageTuLing;
import service.util.httpclient.HttpClientUtils;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/24
 * @description
 */
@RestController
@RequestMapping(value="/talk")
public class TuLingRobot {

    @RequestMapping(value="/{content}", method= RequestMethod.GET)
    public MessageTuLing getTuLingRobot(@PathVariable String  content) throws Exception {
        String respContent= HttpClientUtils.get("http://www.tuling123.com/openapi/api?key=3e2c5281106e4ca3b10d296da39f6d48&info="+content,"utf-8");
        MessageTuLing message= JSON.parseObject(respContent,MessageTuLing.class);
        return message;
    }

}
