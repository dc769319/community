package cc.charles.community;

/**
 * @author Charles
 * @version 1.0
 */
public class VolatileDemo {
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                    System.out.println("子线程在循环");
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
    }
}
