import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    static volatile Boolean isSwitchedOn;

    public static void main(String[] args) {
        System.out.println("Задача 2. Отчет для налоговой");

        LongAdder longAdder = new LongAdder();

        final int NUMBER_OF_SHOPS = 3;


        final ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_SHOPS);
        for (int i = 0; i < NUMBER_OF_SHOPS; i++) {
            threadPool.execute(new Shop(longAdder, i +1));
        }


        final int timeOutLong = 5_000;
        sleep(timeOutLong);



        threadPool.shutdownNow();
        final int timeOutShort = 100;
        sleep(timeOutShort);
        System.out.printf("Конец смены, сумма продаж = %d", longAdder.sum());

        return;

    }

    private static void sleep(int timeOut) {

        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
