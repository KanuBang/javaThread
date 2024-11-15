package thread.start.test;

import static util.MyLogger.log;

public class Start3MainTest {
    public static void main(String[] args) {
        log("main start");

        Thread thread = new Thread(new Runnable() {
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
        });

        thread.start();

        log("main end");
    }
}
