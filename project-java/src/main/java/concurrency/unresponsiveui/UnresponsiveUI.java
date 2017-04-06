package concurrency.unresponsiveui;

import java.io.IOException;

/**
 * Created by tianjian on 2017/3/28.
 */
public class UnresponsiveUI {
    private volatile double d = 1;
    public UnresponsiveUI() throws IOException {
        while(d > 0){
            d += (Math.PI + Math.E);
        }
        System.in.read();
    }
}
