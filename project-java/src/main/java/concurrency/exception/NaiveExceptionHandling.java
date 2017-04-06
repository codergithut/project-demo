package concurrency.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianjian on 2017/4/2.
 */
public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try{
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch(RuntimeException ue) {
            System.out.println("Exception has been handle!");
        }

    }
}
