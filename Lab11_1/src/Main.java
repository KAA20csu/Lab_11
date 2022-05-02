import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int x = 0;
    static int count = 0;

    static class LuckyThread extends Thread {

        @Override
        public void run() {
            while (x < 999999) {
                x++;
                if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                        % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                    System.out.println(x);
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

        // Команда join позволяет дождаться завершения дочернего потока. Разные данные
        // выводились по той причине, что сначала мы запустили все три потока и не ждали
        // завершения конкретного. Стартуя поток, мы давали программе подождать завершения,
        // а потом запускали следующий. Так они стали давать одно и то же значение.
        // Потоки стали работать параллельно и перестали пересекаться, так как join
        // ждал конца потока.
        System.out.println("Total: " + count);
    }
}
