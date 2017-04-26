package templates.client.util.template;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description 获取模板文件接口
 */
public interface GetTemplateByStem {

    String getTemplateByFileName(String fileName);

    String getTemplateByFileName(String fileName, String path);

}
