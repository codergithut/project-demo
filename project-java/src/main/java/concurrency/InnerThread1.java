package concurrency;

/**
 * Created by tianjian on 2017/3/26.
 */
public class InnerThread1 {
    private int countDown = 5;
    private Inner inner;
    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }

        public void run(){
            while(true){
                System.out.println(this);
                if(--countDown == 0){
                    return ;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
        }

        public String toString(){
            return getName() + " : " + countDown;
        }
    }

    public InnerThread1(String name){
        inner = new Inner(name);
    }





}
