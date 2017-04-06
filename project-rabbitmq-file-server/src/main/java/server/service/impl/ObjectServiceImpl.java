package server.service.impl;

import org.dom4j.DocumentException;
import server.config.ConstantConfig;
import server.utils.ChangeXmlFormat;
import server.utils.JaxbUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static server.config.ConstantConfig.ENTITY_PATH;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/24
 * @description 将接收的到文本消息转换为实例对象
 */
public class ObjectServiceImpl implements ConstantConfig {

    public ObjectServiceImpl(){
    }

    public  List<Object> getObject(String content) throws DocumentException, ClassNotFoundException {
        List<Object> objects = new ArrayList<Object>();
        Map<String,String> datas = ChangeXmlFormat.getParamValueByElement(content,"Data");
        for(Map.Entry<String,String> entry : datas.entrySet()){
            Class attrObjClass = Class.forName(ENTITY_PATH + entry.getKey());
            objects.add(JaxbUtil.converyToJavaBean(entry.getValue(), attrObjClass));
        }
        return objects;
    }
}
