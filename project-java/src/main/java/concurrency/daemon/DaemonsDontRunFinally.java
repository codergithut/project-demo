package concurrency.daemon;

/**
 * Created by tianjian on 2017/3/23.
 */
public class DaemonsDontRunFinally {
    public static void main(String[] args){
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
