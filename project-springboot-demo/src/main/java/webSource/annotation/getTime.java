package webSource.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/1/3
 * @description
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface getTime {
    String value() default "我是日志注解";
}
