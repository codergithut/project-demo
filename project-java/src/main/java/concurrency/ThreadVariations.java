package concurrency;

/**
 * Created by tianjian on 2017/3/26.
 */
public class ThreadVariations {
    public static void main(String[] args){
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("innerRunnable2");
        new ThreadMethod("ThreadMehtod").runTask();
    }
}
