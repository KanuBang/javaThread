package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV2 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printThread = new Thread(printer, "printer");
        printThread.start();

        Scanner userInput = new Scanner(System.in);
        while(true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");
            String input = userInput.nextLine();
            if(input.equals("q")) {
                printThread.interrupt();
                break;
            }

            printer.addJob(input);
        }
    }


    static class Printer implements Runnable {
        //volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>(); // 여러 스레드가 동시에 큐에 접근할 때 사용

        @Override
        public void run() {
            // interrupt 상태가 발생하면 Thread.interrupted가 true 라서 반복문이 실행되지 않음.
            while(!Thread.interrupted()) {
                if(jobQueue.isEmpty()) {
                    continue;
                }

                try {
                    String job = jobQueue.poll();
                    log("출력 시작: " + job + ", 대기 문서: " + jobQueue);
                    Thread.sleep(3000); // 출력에 걸리는 시간
                    // 실행 도중에 Interrupt가 발생하면 interrupt 상태가 false이기에 여기서 인터럽트 예외가 발생
                    log("출력 완료: " + job);
                } catch (InterruptedException e) {
                    log("인터럽트");
                    break;
                }
            }
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }


    }
}
