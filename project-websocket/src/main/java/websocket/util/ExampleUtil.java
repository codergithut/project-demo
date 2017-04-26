package websocket.util;


import tk.mybatis.mapper.entity.Example;

import java.util.Iterator;
import java.util.Map;

/**
 * @author <a href="mailto:tianjian@gtmap.cn">tianjian</a>
 * @version 1.0，2017/3/29
 * @description
 */
public class ExampleUtil extends Example {
    public ExampleUtil(Class<?> entityClass, Map<String,Object> param){
        super(entityClass);
        if(param!=null&&param.size()>0){
            for (Iterator it = param.keySet().iterator(); it.hasNext(); ) {
                String key = it.next().toString();
                this.createCriteria().andEqualTo(key,param.get(key));
            }
        }
    }

}