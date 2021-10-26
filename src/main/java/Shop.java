import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Shop extends Thread implements Runnable{

    private LongAdder longAdder;

    public Shop(LongAdder longAdder, int number) {

        super(String.format("Магазин № %d", number));
        this.longAdder = longAdder;
    }

    @Override
    public void run() {
        final int timeOut = 500;

        final int MAX = 100;
        Random random = new Random();



        while (!isInterrupted()) {

            int proceeds = random.nextInt(MAX);
            longAdder.add((long) proceeds);
            System.out.printf("%s продал на %d.\n", this.getName(), proceeds);
            if (!sleep(timeOut))
                return;

        }
    }

    private boolean sleep(int timeOut) {

        try {
            super.sleep(timeOut);
            return true;
        } catch (InterruptedException e) {
            return false;
        }

    }

}
