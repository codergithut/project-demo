package webSource.rabbitmq.service.template;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/20
 * @description
 */
public interface GetTemplateByStem {

    String getTemplateByFileName(String fileName);

    String getTemplateByFileName(String fileName, String path);

}
