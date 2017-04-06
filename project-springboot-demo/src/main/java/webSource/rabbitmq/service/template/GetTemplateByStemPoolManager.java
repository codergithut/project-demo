package webSource.rabbitmq.service.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/20
 * @description
 */
public class GetTemplateByStemPoolManager implements GetTemplateByStem{
    private String type;
    private static GetTemplateByStem getTemplateByStem;
    private static Map<String,GetTemplateByStemPoolManager> getTemplateByStemPoolManagers = new HashMap<String,GetTemplateByStemPoolManager>();


    private GetTemplateByStemPoolManager(String type) {
        getTemplateByStem = GetTemplateByStemXsdImpl.getGetTemplateByStemXsdImp();
    }

    public static GetTemplateByStemPoolManager BildTemplateByStem(String type){
        if((getTemplateByStemPoolManagers.size()==0)||(getTemplateByStemPoolManagers.get(type)==null)){
            GetTemplateByStemPoolManager factory = new GetTemplateByStemPoolManager(type);
            getTemplateByStemPoolManagers.put(type,factory);
        }
        return getTemplateByStemPoolManagers.get(type);
    }

    public String getTemplateByFileName(String fileName){
        return getTemplateByStem.getTemplateByFileName(fileName);
    }

    public String getTemplateByFileName(String fileName,String path){
        return getTemplateByStem.getTemplateByFileName(fileName,path);
    }

    public static void main(String[] args){
        GetTemplateByStemPoolManager factory = GetTemplateByStemPoolManager.BildTemplateByStem("xsd");
        System.out.println(factory.getTemplateByFileName("9000102"));
    }


}
