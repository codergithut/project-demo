package webSource.rabbitmq.service;

import org.dom4j.DocumentException;
import webSource.rabbitmq.service.template.GetTemplateByStem;
import webSource.rabbitmq.service.template.GetTemplateByStemPoolManager;
import webSource.rabbitmq.utils.FileUtil;
import webSource.rabbitmq.utils.XmlUtil;
import webSource.rabbitmq.utils.XmlValidateResult;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/2/22
 * @description
 */
public class ScannerXmlFile
{
    private static GetTemplateByStem getTemplateByStem;

    public static Map<String,String> getXmlFile(File file) throws DocumentException, IOException {
        Map<String,String> content = new HashMap<String,String>();
        StringBuffer error = new StringBuffer();

        String fileString= FileUtil.getFileString(file);
        getTemplateByStem = GetTemplateByStemPoolManager.BildTemplateByStem("xsd");
        Map<String,String> head = XmlUtil.getParamValueByElement(fileString,"Head");
        content.putAll(head);
        String serverNumber = null;
        if(head!=null){
            serverNumber = head.get("RegType");
        }

        String xsdString = getTemplateByStem.getTemplateByFileName(serverNumber);

        XmlValidateResult result = XmlUtil.checkXmlByXsd(fileString,xsdString);

        if(fileString!=null&&result.isValidated()){
            content.put("content",fileString);
        }
        if(fileString==null){
            error.append("文件读取错误！");
        }
        if(!result.isValidated()){
            error.append("文件验证错误！错误原因为:"+result.getErrorMsg()+"请及时修正！");
        }
        content.put("error",error.toString());
        return content;
    }
}
