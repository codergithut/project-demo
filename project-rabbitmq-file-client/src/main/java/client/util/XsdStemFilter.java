package client.util;

import java.io.File;
import java.io.FileFilter;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0, 2017/3/23
 * @description xsd文件过滤器
 */
public class XsdStemFilter implements FileFilter{
    public boolean accept(File path) {
        return path.getName().toLowerCase().endsWith(".xsd");
    }
}
