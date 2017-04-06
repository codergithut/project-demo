package concurrency.daemon;

/**
 * Created by tianjian on 2017/3/22.
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All Daemons started");
        Thread.sleep(1750);
    }
}
