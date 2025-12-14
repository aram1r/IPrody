import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main{
    public static void main(String[] args) {
        //1
        topTenNumbers();

        //2
        ArrayList<Integer> arrayList =  new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            arrayList.add(i);
        }

        Predicate<Integer> isEven = i -> i % 2 == 0;

        System.out.println(filterT(arrayList, isEven));


        //3
        Predicate<String>  stringPredicate = word -> word.length() > 5;
        ArrayList<String> stringArrayList =  new ArrayList<>();
        stringArrayList.add("Hello");
        stringArrayList.add("World");
        stringArrayList.add("Longer");
        stringArrayList.add("Cosmonaut");
        System.out.println(filterAndJoin(stringArrayList, stringPredicate));

        //4
        ArrayList<Integer> arrayList2 =  new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            arrayList2.add((int) (Math.random()*100+1));
        }
        System.out.println(filterAndSort(arrayList2, "DESC"));

        //5
        System.out.println(returnFactorial(5L));

        //6
        Camp camp = new Camp();
        camp.addScout(new BoyScout("1", 10, Team.RED));
        camp.addScout(new BoyScout("2", 8, Team.BLUE));
        camp.addScout(new BoyScout("3", 13, Team.BLUE));
        camp.addScout(new BoyScout("4", 12, Team.BLUE));
        camp.addScout(new BoyScout("5", 17, Team.RED));
        camp.addScout(new BoyScout("6", 16, Team.BLUE));
        camp.addScout(new BoyScout("7", 14, Team.RED));
        camp.addScout(new BoyScout("8", 11, Team.RED));
        camp.addScout(new BoyScout("9", 22, Team.RED));
        camp.addScout(new BoyScout("10", 25, Team.RED));
        System.out.println(camp.returnBoyScouts());
    }

    public static void topTenNumbers() {
        ArrayList<Integer> list = new ArrayList<>(100);
        for (int i = 1; i <= 100; i++) {
            list.add((int) (Math.random()*1000+1));
        }
        ArrayList<Integer> minimum = list.stream().sorted().limit(10).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(minimum);
    }

    public static <T> ArrayList<T> filterT (ArrayList<T> array, Predicate<T> predicate) {
        return array.stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
    }

    public static String filterAndJoin (ArrayList<String> arrayList, Predicate<String> predicate) {
        return arrayList.stream().filter(predicate).collect(Collectors.joining("|"));
    }
    public static ArrayList<Integer> filterAndSort (ArrayList<Integer> arrayList, String order) {
        if (order.equalsIgnoreCase("asc")) {
            return arrayList.stream().distinct().sorted(Comparator.naturalOrder()).collect(Collectors.toCollection(ArrayList::new));
        } else if (order.equalsIgnoreCase("desc")) {
            return arrayList.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
        }
        return null;
    }

    public static Long returnFactorial(Long number) {
        return LongStream.rangeClosed(1, number).reduce(1, (a, b) -> a * b);
    };
}
