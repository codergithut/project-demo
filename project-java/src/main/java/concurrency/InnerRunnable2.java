package concurrency;

/**
 * Created by tianjian on 2017/3/26.
 */
public class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;
    public InnerRunnable2(String name){
        t = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    System.out.println(this);
                    if(--countDown == 0){
                        return ;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("sleep()" + " : " + countDown);
                    }
                }
            }

            public String toString(){
                return Thread.currentThread().getName() + " : " + countDown;
            }
        },name);
        t.start();
    }
}
