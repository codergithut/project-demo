package concurrency.daemon;

/**
 * Created by tianjian on 2017/3/23.
 */
public class DaemonSpawn implements Runnable {
    @Override
    public void run() {
        while(true){
            Thread.yield();
        }
    }
}
