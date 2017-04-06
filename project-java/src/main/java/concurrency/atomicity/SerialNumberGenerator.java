package concurrency.atomicity;

/**
 * Created by tianjian on 2017/4/6.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public synchronized static int nextSerialNumber() {
        return serialNumber++;
    }
}
