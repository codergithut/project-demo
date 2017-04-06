package concurrency.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * Created by tianjian on 2017/3/22.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
