import util.ProtostuffUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/14
 * @description
 */
public class ProtostuffTest {
    public static void main(String[] args) {
        List<PersonDemo> data = new ArrayList<PersonDemo>();
        PersonDemo person = new PersonDemo();
        person.setName("test");
        person.setSex("man");
        //data.add(person);
        byte[] sss = ProtostuffUtil.serializer(data);
        Object o = ProtostuffUtil.deserializer(ProtostuffUtil.serializer(data), data.getClass());
        if(o instanceof  List) {
            List<PersonDemo> ss = (List<PersonDemo>)o;
        }
    }

}
