package concurrency;

/**
 * Created by tianjian on 2017/3/21.
 */
public class BasicThreads {
    public static void main(String[] args){
        Thread t =new Thread(new LifeOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
