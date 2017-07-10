package util.export;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/27
 * @description
 */
public class GetBeanData {

    List changeObject = new ArrayList();

    public List<Map<String,String>> allDatas = new ArrayList<Map<String,String>>();

    public Map<String,String> metadata = new HashMap<String,String>();

    public GetBeanData(List changeObject){
        this.changeObject = changeObject;
    }

    public List<Map<String,String>> getAllDatas() throws InvocationTargetException {
        Map<String,String> data = new HashMap<String,String>();
        for(Object model : changeObject ){
            Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
            try {
                for (int j = 0; j < field.length; j++) { // 遍历所有属性
                    String name = field[j].getName(); // 获取属性的名字
                    name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    String type = field[j].getGenericType().toString(); // 获取属性的类型
                    if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                        Method m = model.getClass().getMethod("get" + name);
                        String value = (String) m.invoke(model); // 调用getter方法获取属性值
                        if (value == null) {
                            m = model.getClass().getMethod("set"+name,String.class);
                            m.invoke(model, "");
                        }
                        data.put(name,value);
                    }
                    if (type.equals("class java.lang.Integer")) {
                        Method m = model.getClass().getMethod("get" + name);
                        Integer value = (Integer) m.invoke(model);
                        if (value == null) {
                            m = model.getClass().getMethod("set"+name,Integer.class);
                            m.invoke(model, 0);
                        }
                        data.put(name,value + "");
                    }
                    if (type.equals("class java.lang.Boolean")) {
                        Method m = model.getClass().getMethod("get" + name);
                        Boolean value = (Boolean) m.invoke(model);
                        if (value == null) {
                            m = model.getClass().getMethod("set"+name,Boolean.class);
                            m.invoke(model, false);
                        }
                        data.put(name,value.toString());
                    }
                    if (type.equals("class java.util.Date")) {
                        Method m = model.getClass().getMethod("get" + name);
                        Date value = (Date) m.invoke(model);
                        if (value == null) {
                            m = model.getClass().getMethod("set"+name,Date.class);
                            m.invoke(model, new Date().toString());
                        }
                        data.put(name,value.toString());
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            allDatas.add(data);
        }
        return allDatas;
    }

}
