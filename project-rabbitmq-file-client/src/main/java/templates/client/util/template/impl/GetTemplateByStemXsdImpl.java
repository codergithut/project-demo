package templates.client.util.template.impl;

import templates.client.util.FileUtil;
import templates.client.util.XsdStemFilter;
import templates.client.util.template.GetTemplateByStem;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 获取xml模板文件实现类
 */
public class GetTemplateByStemXsdImpl implements GetTemplateByStem {

    private Map<String,String> data = new HashMap<String,String>();
    private static GetTemplateByStemXsdImpl getTemplateByStemXsd;

    @Value("${template.file.path}")
    private String templatePath;

    private void InitXsdData(String path) {
        XsdStemFilter xsdStemFilter = new XsdStemFilter();
        List<File> files = FileUtil.getFiles(path);
        for(File f:files){
            if(xsdStemFilter.accept(f)){
                System.out.println(f.getName());
                data.put(f.getName().split("\\.")[0],FileUtil.getFileString(f));
            }
        }
    }

    private GetTemplateByStemXsdImpl(){
        InitXsdData("D:\\project\\trunk\\templates.client\\src\\main\\resources\\service");
    }

    public static GetTemplateByStem getGetTemplateByStemXsdImp(){
        if(getTemplateByStemXsd==null){
            return new GetTemplateByStemXsdImpl();
        }
        return getTemplateByStemXsd;
    }

    public String getTemplateByFileName(String fileName){
        String tempString = data.get(fileName);
        if(tempString!=null){
            return tempString;
        }
        return null;
    }

    public String getTemplateByFileName(String fileName,String path){
        InitXsdData(path);
        return data.get(fileName);
    }
}
