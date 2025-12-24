import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        ThreadSafeList<String> list = new ThreadSafeList<String>(String.class);
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            Thread thread = new Thread(() -> {
//                System.out.println("Поток " + finalI + " начал добавление");
//                list.add(finalI + "");
//                System.out.println("Поток " + finalI + " закончил добавление");
//            });
//            thread.start();
//            thread.join();
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i));
//        }
//
//        for (int i = 0; i<100; i++) {
//            int finalI = i;
//            Thread thread = new Thread(() -> {
//                System.out.println("Поток " + finalI + " получил доступ к переменной " + list.get(3));
//            });
//            thread.start();
//            thread.join();
//        }

        PetrolStation station = new PetrolStation(10_000.0);
        List<Thread> threads = new ArrayList<>();
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i<30; i++) {
            int finalI = i;

            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    station.doTank((double) Math.round(Math.random()*2000.0), finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}