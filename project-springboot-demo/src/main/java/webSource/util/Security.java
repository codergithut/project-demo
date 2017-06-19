package webSource.util;

import org.springframework.stereotype.Service;
import webSource.annotation.encrypt;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/19
 * @description
 */
public abstract class Security {

    /**
     * @param encrypted
     *            密文字节数组
     *            密钥
     * @return 解密后的字符串
     */
    public abstract String decrypt(String encrypted);
    /**
     *            密钥
     * @param clearPwd
     *            明文字符串
     * @return 密文字节数组
     */
    public abstract String encrypt(String clearPwd);


    public abstract byte[] getDecrKey();

    public abstract byte[] getEncrKey();


    Map<Class<?>,List<String>> classParams = new HashMap<Class<?>,List<String>>();


    private void dealField(Object source, byte[] key, int type) throws InvocationTargetException, IllegalAccessException {
        Method[] sourceMethods = source.getClass().getMethods();
        String encryptBefore = null;
        Class<?> c = source.getClass();
        List<String> encryptFields = getAllAnnoationInfo(c);

        if(encryptFields.size() > 0) {
            for (int i = 0; i < sourceMethods.length; i++) {
                if (sourceMethods[i].getName().startsWith("get")) {
                    String lsName = sourceMethods[i].getName().substring(3);// 属性
                    if (encryptFields.contains(lsName.toLowerCase())) {
                        Object loValue = sourceMethods[i].invoke(source, null);  // 值
                        encryptBefore = loValue.toString();
                    }
                }
            }


            for (int i = 0; i < sourceMethods.length; i++) {
                if (sourceMethods[i].getName().startsWith("set")) {
                    String lsName = sourceMethods[i].getName().substring(3);// 属性
                    if (encryptFields.contains(lsName.toLowerCase())) {
                        //todo 加密
                        String ss = decrypt(encryptBefore);
                        if(type ==1) {
                            sourceMethods[i].invoke(source, encrypt(encryptBefore));
                        } else if(type ==2){
                            sourceMethods[i].invoke(source, decrypt(encryptBefore));
                        }
                    }
                }
            }
        }
    }

    public void EncryptField(Object source, byte[] key) throws InvocationTargetException, IllegalAccessException {
        dealField(source, key, 1);
    }

    public void DecryptField(Object source, byte[] key) throws InvocationTargetException, IllegalAccessException {
        dealField(source, key, 2);
    }

    public List<String> getAllAnnoationInfo(Class<?> o) {
        if(classParams.get(o) != null) {
            return classParams.get(o);
        } else {
            List<String> datas = new ArrayList<String>();
            Field[] field = o.getDeclaredFields();
            for (Field f : field) {
                encrypt encrypt = f.getAnnotation(encrypt.class);
                if (encrypt != null) {
                    datas.add(f.getName());
                    continue;
                }
            }
            classParams.put(o,datas);
            return datas;
        }
    }

}
