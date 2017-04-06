package webSource.rabbitmq.service.template;

import java.io.File;
import java.io.FileFilter;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/20
 * @description
 */
public class XsdStemFilter implements FileFilter{
    @Override
    public boolean accept(File path) {
        return path.getName().toLowerCase().endsWith(".xsd");
    }
}
