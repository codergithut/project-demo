package concurrency.atomicity;

import concurrency.resource.EvenChecker;
import concurrency.resource.IntGenerator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tianjian on 2017/4/6.
 */
public class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger currentEvenValue = new AtomicInteger(0);
    public int next() {
        return currentEvenValue.addAndGet(2);
    }
    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
