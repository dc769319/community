package cc.charles.community;

/**
 * @author Charles
 * @version 1.0
 */
public class SynchronizedDemo implements Runnable {

    private static Integer count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        synchronized (SynchronizedDemo.class) {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }
}
