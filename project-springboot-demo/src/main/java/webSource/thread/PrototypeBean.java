package webSource.thread;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/30
 * @description
 */
@Scope("prototype")
@Component
@Service
public class PrototypeBean implements Runnable{
    private int number ;

    public PrototypeBean() {
    }

    public void addNumber() {
        number++;
    }

    public void sayPrototypeBean() {
        System.out.println("测试ThreadPool以及Prototype测试效果 number :" + number);
    }

    @Override
    public void run() {
        while(number < 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number ++;
            System.out.println("测试ThreadPool以及Prototype测试效果 number :" + number + ", HashCode　:" + this.toString());

        }

    }
}
