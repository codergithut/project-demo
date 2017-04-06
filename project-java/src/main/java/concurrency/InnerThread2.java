package concurrency;

/**
 * Created by tianjian on 2017/3/26.
 */
public class InnerThread2 {
    private int countDown = 5;
    private Thread t;
    public InnerThread2(String name) {
        t = new Thread(name){
            public void run(){
                while(true){
                    System.out.println(this);
                    if(--countDown == 0){
                        return ;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("sleep() interrupted");
                    }
                }
            }

            public String toString() {
                return getName() + " : " + countDown;
            }
        };
        t.start();
    }
}
