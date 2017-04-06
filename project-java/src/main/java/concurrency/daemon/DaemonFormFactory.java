package concurrency.daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianjian on 2017/3/22.
 */
public class DaemonFormFactory implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread() + " " +this);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i = 0; i < 10; i++){
            exec.execute(new DaemonFormFactory());
        }
        System.out.println("All daemons started");
        Thread.sleep(5000);
    }
}
