package concurrency.daemon;

/**
 * Created by tianjian on 2017/3/23.
 */
public class ADaemon implements Runnable {
    @Override
    public void run() {
        try{
            System.out.println("Starting ADaemon");
            Thread.sleep(1);
        } catch (InterruptedException e){
            System.out.println("Exiting via InterruptedException");
        } finally {
            System.out.println("this should always run?");
        }

    }
}
