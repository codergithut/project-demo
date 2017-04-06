package concurrency.daemon;

import static java.lang.Thread.sleep;

/**
 * Created by tianjian on 2017/3/23.
 */
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon() + ". ");
        sleep(10000);
    }
}
