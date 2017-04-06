package server.utils;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 根据已知的xml字符串转化为可以被程序解析为bean的字符串
 */
public class ChangeXmlFormat {
    static Map<String,String> reflectData = new HashMap<String,String>();
    static Map<String,String> reflectClass = new HashMap<String,String>();
    static{
        //TODO:可以到文件中读取数据比如XML初始化这2个实体
        reflectData.put("ZTT_GY_QLR","baQLR");
        reflectClass.put("baQLR","BA_QLR");
    }

    /**
     *
     * @param fileContent
     * @param parentElement
     * @return
     * @throws
     * @description 提取XML<DATA/> 节点中的数据并将属性名称转为小写 xml转换为bean工具的需要 并将数据通过map返回给调用方
     */
    public static Map<String,String> getParamValueByElement(String fileContent,String parentElement)
            throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc;
        Element sonElement;
        Document sonDoc;
        String className;
        String elementName;
        doc = DocumentHelper.parseText(fileContent);
        Map<String,String> data = new HashMap<String,String>();
        Element root = doc.getRootElement();
        Element son = root.element(parentElement);
        List<Element> elements = son.elements();
        for(Element e : elements){
            elementName = e.getName();
            className = (reflectData.get(elementName) == null ? elementName : reflectData.get(elementName));
            sonElement = DocumentHelper.createElement(className);
            List<Attribute> attributes = e.attributes();
            for(Attribute attr : attributes){
                sonElement.setAttributeValue(attr.getName().toLowerCase(),attr.getText());
            }
            sonDoc = DocumentHelper.createDocument(sonElement);
            String s = sonDoc.asXML();
            String key = reflectClass.get(className) == null ?  className: reflectClass.get(className);
            data.put(key, s);
        }

        return data;
    }
}
