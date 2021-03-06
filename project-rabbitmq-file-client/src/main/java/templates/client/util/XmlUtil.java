package templates.client.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description xml工具封装
 */
public class XmlUtil {

    /**
     *
     * @param fileContent 需要抽取的字符串
     * @param parentElement 父节点名称
     * @return
     * @throws
     * @description 获取xml特定节点的信息 对节点的样式有比较严格的规范
     * 获取的样板如下
     * <head>
     *     <name>person</name>
     *     <sex>sex</sex>
     * </head>
     * 可以获取head并返回包含key分别为name和sex value分别为person和sex的map
     */
    public static Map<String,String> getParamValueByElement(String fileContent,String parentElement) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc;
        doc = DocumentHelper.parseText(fileContent);

        String name = null;
        String value = null;
        Map<String,String> data = new HashMap<String,String>();
        Element root = doc.getRootElement();
        Element head = root.element(parentElement);
        List<Element> list = head.elements();
        for(Element e: list){
            name = e.getName().trim();
            value = e.getText().trim();
            data.put(name,value);
        }

        return data;
    }

    /**
     *
     * @param xmlStr 需要验证的xml字符串
     * @param xsdStr 验证xml的xsd字符串
     * @return 包含验证结果的对象
     */
    public static XmlValidateResult checkXmlByXsd(String xmlStr,String xsdStr){

        // 创建返回值类，默认为失败
        XmlValidateResult vs = new XmlValidateResult();

        if(xmlStr==null || xsdStr==null){
            return vs;
        }
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
// 包装待验证的xml字符串为Reader
        Reader xmlReader = new BufferedReader(new StringReader(xmlStr));
// 保障Schema xsd字符串为Reader
        Reader xsdReader = new BufferedReader(new StringReader(xsdStr));
        try {
// 构造Schema Source
            Source xsdSource = new StreamSource(xsdReader);
// 解析作为Schema的指定源并以Schema形式返回它
            Schema schema = factory.newSchema(xsdSource);
// 根据Schema检查xml文档的处理器,创建此 Schema的新 validator
            Validator validator = schema.newValidator();
// 构造待验证xml Source
            Source xmlSource = new StreamSource(xmlReader);
// 执行验证
            validator.validate(xmlSource);
// 设置验证通过
            vs.setValidated(true);
            return vs;
        } catch (SAXException ex) {
// 设置验证失败
            vs.setValidated(false);
// 设置验证失败信息
            vs.setErrorMsg(ex.getMessage());
            return vs;
        } catch (IOException e) {
// 设置验证失败
            vs.setValidated(false);
// 设置验证失败信息
            vs.setErrorMsg(e.getMessage());
            return vs;
        }
    }


}
