package thread.start.test;

import static util.MyLogger.log;

public class Start2MainTest {
    public static void main(String[] args) {
        log("main start");

        Thread thread = new Thread(new CounterRunnable(), "counter");
        thread.start();

        log("main end");
    }


    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            for(int i = 1; i <= 5; i++) {
                log("value: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
