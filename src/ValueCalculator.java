import java.util.stream.IntStream;

public class ValueCalculator{
    public static void doCalc() {
        long now = System.currentTimeMillis();
        Integer[] array = new Integer[1_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 3;
        }
        Thread firstThread = new Thread(() -> {
            IntStream.range(0, array.length/2).forEach(i -> {array[i] = (int) (array[i] * Math.sin(0.2f + (double) i / 5) * Math.cos(0.2f + (double) i / 5) * Math.cos(0.4f + (double) i / 2));
                System.out.println(i);});
        });
        Thread secondThread = new Thread(() -> {
            IntStream.range(array.length/2, array.length-1).forEach(i -> {array[i] = (int) (array[i] * Math.sin(0.2f + (double) i / 5) * Math.cos(0.2f + (double) i / 5) * Math.cos(0.4f + (double) i / 2));
                System.out.println(i);});
        });
        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(System.currentTimeMillis()-now + " ms");
    };
}
