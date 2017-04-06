package concurrency;

/**
 * Created by tianjian on 2017/3/26.
 */
public class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;
    public ThreadMethod(String name) {
        this.name = name;
    }
    public void runTask() {
        if(t == null){
            t = new Thread(name) {
                public void run() {
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
            };
            t.start();
        }
    }
}
