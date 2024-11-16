package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinTestMain {

    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(new MyTask(), "t1");
        Thread thread2 = new Thread(new MyTask(), "t2");
        Thread thread3 = new Thread(new MyTask(), "t3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("모든 스레드 실행 완료");

    }
    static class MyTask implements Runnable {
        @Override
        public void run() {
            for(int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}
