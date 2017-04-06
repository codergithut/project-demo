package concurrency;

/**
 * Created by tianjian on 2017/3/26.
 */
public class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;
    private class Inner implements Runnable {
        Thread t;
        Inner(String name) {
            t = new Thread(this , name);
            t.start();
        }

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
                    System.out.println("sleep() interrupted");
                }
            }
        }

        public String toString(){
            return t.getName() + " : " + countDown;
        }
    }

    public InnerRunnable1 (String name){
        inner = new Inner(name);
    }
}
