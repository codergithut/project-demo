import util.bean.TestBean;
import util.export.GetBeanData;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/27
 * @description
 */
public class Test {

    public static void main(String[] args) throws InvocationTargetException {
        List<TestBean> data = new ArrayList<TestBean>();
        for(int i = 0; i < 10; i++) {
            TestBean testBean = new TestBean();
            testBean.setAge(i);
            testBean.setDate(new Date());
            testBean.setTest("this is test");
            data.add(testBean);
        }

        GetBeanData get = new GetBeanData(data);
        List<Map<String,String>> datas = get.getAllDatas();
        System.out.println(datas);

    }

}
