package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/7
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Demo {

    String str();

    int val();
}