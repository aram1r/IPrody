import phonebook.PhoneBook;
import phonebook.Record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("dog");
        list.add("dog");
        list.add("cat");
        list.add("bark");
        list.add("meow");
        list.add("dog");
        list.add("bobcat");
        list.add("ostrich");
        list.add("lion");
        list.add("cheetah");
        list.add("wolf");
        list.add("bird");
        list.add("dog");
        list.add("dog");
        list.add("dog");
        list.add("dog");

        System.out.println(countOccurance(list,"dog"));

        System.out.println(calcOccurrence(list));


        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(new Record("Alex", "1234"));
        phoneBook.add(new Record("John", "1234"));
        phoneBook.add(new Record("Jim", "12345"));
        phoneBook.add(new Record("John", "123467"));
        phoneBook.add(new Record("Alex", "12346789"));
        phoneBook.add(new Record("Alex", "45123467"));
        phoneBook.add(new Record("Alex", "23123467"));


        System.out.println(phoneBook);

        System.out.println(phoneBook.find("Alex"));
        System.out.println(phoneBook.find("Roy"));

        System.out.println(phoneBook.findAll("Alex"));
    }

    static int countOccurance(ArrayList<String> list, String word) {
        int count = 0;
        if (list.contains(word)) {
            for (String str : list) {
                if (str.equals(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    static ArrayList<Object> returnArrayList (Object[] objects) {
        ArrayList<Object> list = new ArrayList<>();
        if (objects != null && objects.length > 0) {
            list.addAll(Arrays.asList(objects));
        }
        return list;
    }

    static ArrayList<Integer> findUnique(ArrayList<Integer> list) {
        ArrayList<Integer> uniqueList = new ArrayList<>();
        for (Integer integer : list) {
            if (!uniqueList.contains(integer)) {
                uniqueList.add(integer);
            }
        }
        return uniqueList;
    }

    static ArrayList<Word> calcOccurrence (List<String> list) {
        ArrayList<Word> result = new ArrayList<>();
        for ( int i = 0; i<list.size(); i++ ) {
            Word word = new Word(list.get(i));
            if (!result.contains(word)) {
                result.add(word);
            } else {
                int index = result.indexOf(word);
                int occ = result.get(index).getOccurrence()+1;
                result.get(index).setOccurrence(occ);
            }
        }
        return result;
    }
}