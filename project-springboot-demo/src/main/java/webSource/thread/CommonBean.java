package webSource.thread;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/6/30
 * @description
 */
@Component
@Service
public class CommonBean implements Runnable{
    private int number ;

    public void addNumber() {
        number++;
    }

    public void sayCommonBean() {
        System.out.println("测试ThreadPool以及Common测试效果 number :" + number);
    }

    @Override
    public void run() {
        boolean flag = true;
        while(flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            number ++;
            System.out.println("测试ThreadPool以及Common测试效果 number :" + number + ", HashCode :" + this.toString());
        }
    }
}
