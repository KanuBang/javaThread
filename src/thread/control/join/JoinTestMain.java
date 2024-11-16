package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinTestMain {

    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(new MyTask(), "t1");
        Thread thread2 = new Thread(new MyTask(), "t2");
        Thread thread3 = new Thread(new MyTask(), "t3");

        // 3개 동시에 병렬적으로수행
        thread1.start();
        thread2.start();
        thread3.start();
        
        thread1.join();
        thread2.join();
        thread3.join();

        // join이 없으면 다른 thread가 일하는 동안에 main 스레드가 먼저 꺼질 수도 있다.

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
