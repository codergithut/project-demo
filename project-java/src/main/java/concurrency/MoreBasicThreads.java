package concurrency;

/**
 * Created by tianjian on 2017/3/21.
 */
public class MoreBasicThreads {
    public static void main(String[] args){
        for(int i = 0; i < 5; i++){
            new Thread(new LifeOff()).start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
