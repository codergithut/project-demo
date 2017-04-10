package annotation;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/4/7
 * @description
 */
public class PackageDemo {

    // set values for the annotation
    @Demo(str = "Demo Annotation", val = 100)
    // a method to call in the main
    public static void example() {
        PackageDemo ob = new PackageDemo();

        try {
            Class c = ob.getClass();

            // get the method example
            Method m = c.getMethod("example");

            // get the annotation for class Demo
            Demo annotation = m.getAnnotation(Demo.class);

            // print the annotation
            System.out.println(annotation.str() + " " + annotation.val());
        } catch (NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String args[]) {
        example();
        TestDemo test = new TestDemo();
        Class t = test.getClass();
        Package[] pack = Package.getPackages();
        // check if annotation hello exists
//        for (int i = 0; i < pack.length; i++) {
//            System.out.println("" + pack[i].isAnnotationPresent(Demo.class));
//        }
        System.out.println(t.isAnnotationPresent(Demo.class));
    }
}