package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianjian on 2017/3/21.
 */
public class SingleThreadExecutor {
    public static void main(String[] args){
        ExecutorService exe = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++){
            exe.execute(new LifeOff());
        }
        exe.shutdown();
    }
}
