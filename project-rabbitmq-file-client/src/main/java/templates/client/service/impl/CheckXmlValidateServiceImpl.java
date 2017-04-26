package templates.client.service.impl;

import templates.client.service.CheckXmlValidateService;
import templates.client.util.XmlUtil;
import templates.client.util.XmlValidateResult;
import templates.client.util.template.GetTemplateByStem;
import templates.client.util.template.impl.GetTemplateByStemPoolManager;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 检测xml文件和模板是否匹配的实现类
 */
@Service
public class CheckXmlValidateServiceImpl implements CheckXmlValidateService {
    private static GetTemplateByStem getTemplateByStem;

    public  boolean CheckXmlFile(String fileString, String serverNumber) {

        getTemplateByStem = GetTemplateByStemPoolManager.BildTemplateByStem("xsd");
        String xsdString = getTemplateByStem.getTemplateByFileName(serverNumber);
        if(xsdString == null){
            return false;
        }

        XmlValidateResult result = XmlUtil.checkXmlByXsd(fileString,xsdString);
        return result.isValidated();
    }
}
