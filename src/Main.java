import fruits.Apple;
import fruits.Box;
import fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        Integer[] list = new Integer[12];
        for (int i = 0; i < list.length; i++) {
            list[i] = i+1;
        }
        switchPlaces(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println(arrayToList(list));

        Apple apple = new Apple();
        System.out.println(apple);

        Box<Apple> appleBox = new Box<>(Apple.class);
        appleBox.addFruit(apple);
        appleBox.addFruit(new Apple());

        Box<Orange> orangeBox = new Box<>(Orange.class);
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());

        System.out.println("Вес коробки с апельсинами " + orangeBox.getWeight());
        System.out.println("Вес коробки с яблоками " + appleBox.getWeight());

        System.out.println(orangeBox.compare(appleBox));

        Box<Apple> appleBox2 = new Box<>(Apple.class);
        appleBox.transfer(appleBox2);

        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());
    }

    static <T> void  switchPlaces (T[] array) {
        for (int i = 0; i < array.length; i+=2) {
            if (i % 2 == 0 && i+1 <=array.length-1) {
                var temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }
    }

    static <T> List<T> arrayToList (T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}

