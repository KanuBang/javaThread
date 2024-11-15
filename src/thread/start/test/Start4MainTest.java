package thread.start.test;

import static util.MyLogger.log;

public class Start4MainTest {

    public static void main(String[] args) {
        Thread threadA = new Thread(new PrintRunnable("A", 1000), "Thread-A");
        Thread threadB = new Thread(new PrintRunnable("B", 500), "Thread-B");

        threadA.start();
        threadB.start();
    }

    static class PrintRunnable implements Runnable {

        private String content;
        private int sleepMs;

        public PrintRunnable(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
