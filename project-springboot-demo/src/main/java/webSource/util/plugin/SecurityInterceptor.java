package webSource.util.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import webSource.util.AESUtil;
import webSource.util.Security;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

/**
 * 拦截器作用：给各实体对象在增加、修改时，自动添加操作属性信息。
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }),
        @Signature(type=Executor.class,method="query",args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})
})
public class SecurityInterceptor implements Interceptor
{
    Security security;

    private byte[] key = AESUtil.getRawKey("test");

    public Object intercept(Invocation invocation) throws Throwable
    {
        Object[] args = invocation.getArgs();
        boolean query = false;
        boolean update = false;
        Object returnValue = null;
        List<String> params = null;

        MappedStatement map ;

        System.out.println("-----------参数拦截---------------------------------------------------");
        System.out.println("02 当前线程ID:"+Thread.currentThread().getId());
        //遍历处理所有参数，update方法有两个参数，参见Executor类中的update()方法。
        for(int i=0;i<args.length;i++)
        {
            Object arg=args[i];
            if(arg != null) {
                String className=arg.getClass().getName();
                System.out.println(i + " 参数类型："+className);
            }

            //第一个参数处理。根据它判断是否给“操作属性”赋值。
            if(arg instanceof MappedStatement)
            {//如果是第一个参数 MappedStatement
                MappedStatement ms = (MappedStatement)arg;
                SqlCommandType sqlCommandType = ms.getSqlCommandType();
                System.out.println("操作类型："+sqlCommandType);
                if(sqlCommandType == SqlCommandType.INSERT || sqlCommandType== SqlCommandType.UPDATE)
                {//如果是“增加”或“更新”操作，则继续进行默认操作信息赋值。否则，则退出
                    update = true;
                    continue;
                } else if(sqlCommandType == SqlCommandType.SELECT) {
                    if(arg instanceof MappedStatement) {
                        map = (MappedStatement) arg;
                        /**
                         * 获取返回值类型，单表插件可以放心使用，如果对象不同需要分情况讨论，暂时没有处理
                         */
                        params = security.getAllAnnoationInfo(map.getResultMaps().get(0).getType());
                    }
                    query = true;
                    continue;
                }
                else
                {
                    break;
                }
            }

            if(update) {
                //第二个参数处理。（只有第二个程序才能跑到这）
                if (arg instanceof Map)
                {//如果是map，有两种情况：（1）使用@Param多参数传入，由Mybatis包装成map。（2）原始传入Map
                    System.out.println("这是一个包装过的类型!");
                    Map maps=(Map)arg;
                    for (Object obj : maps.values())
                    {
                        setProperty(obj);
                    }
                }
                else
                {//原始参数传入
                    setProperty(arg);
                }
                update = false;
            }

            if(query) {
                if(params!= null && params.size() > 0 && arg instanceof Map) {
                    setQueryMap((Map)arg, params);
                }
                query = false;
            }

            returnValue = invocation.proceed();

            if(returnValue != null && returnValue instanceof ArrayList<?>){
                List<?> list = (ArrayList<?>)returnValue;
                for(Object val:list){
                    security.DecryptField(val);
                }
                break;
            }
        }

        return returnValue;

    }

    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public void setProperty(Object o) throws InvocationTargetException, IllegalAccessException, ParseException {
        security.EncryptField(o);
    }

    public void setQueryMap(Map map, List<String> params) {
        Iterator<String> iters = map.keySet().iterator();
        while(iters.hasNext()) {
            String param = iters.next();
            if(params.contains(param)) {
                map.put(param, security.encrypt(map.get((Object)param).toString()));
            }

        }

    }


    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

}
