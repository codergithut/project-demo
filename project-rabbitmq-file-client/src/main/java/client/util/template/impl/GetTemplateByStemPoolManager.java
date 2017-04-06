package client.util.template.impl;


import client.util.template.GetTemplateByStem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 根据业务号获取模板文件用于xml验证的资源管理器
 */
public class GetTemplateByStemPoolManager implements GetTemplateByStem {
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

}
