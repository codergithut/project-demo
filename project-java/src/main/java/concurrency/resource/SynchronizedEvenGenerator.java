package concurrency.resource;

/**
 * Created by tianjian on 2017/4/5.
 */
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEventValue = 0;

    public synchronized int next() {
        ++currentEventValue;
        Thread.yield();
        ++currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
