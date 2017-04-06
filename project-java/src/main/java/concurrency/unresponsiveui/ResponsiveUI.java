package concurrency.unresponsiveui;

import java.io.IOException;

/**
 * Created by tianjian on 2017/3/28.
 */
public class ResponsiveUI extends Thread {
    private static volatile double d = 1;
    public ResponsiveUI(){
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while(true) {
            d += (Math.PI + Math.E);
        }
    }

    public static void main(String[] args) throws IOException {
        //new UnresponsiveUI();
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);

    }

}
