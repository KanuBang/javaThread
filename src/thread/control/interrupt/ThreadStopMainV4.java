package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            while(!Thread.interrupted()) {
                // 인터럽트 상태라면 true를 반환, 해당 스레드의 인터럽트 상태를 false로 변경
                // 인터럽트 상태가 아니라면 false를 반환, 해당 스레드의 인터럽트 상태를 유지
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(1000);
                log("자원 정리 종료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태3 = " + Thread.currentThread().isInterrupted());
            }

            log("작업 종료");

            // 자바는 인터럽트 예외가 한 번 발생하면, 스레드의 인터럽트 상태를 다시 정상(false)으로 돌린다.
            // 스레드의 인터럽트 상태를 정상으로 돌리지 않으면 이후에도 계속 인터럽트가 발생하게 된다.
            // 인터럽트의 목적을 달성하려면 인터럽트 상태를 다시 정상으로 돌려두어야 한다.

        }
    }
}
