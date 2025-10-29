import java.util.Random;
import java.util.Scanner;

public class Dictionary{
    private final String[] wordList = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
            "pear", "pepper", "pineapple", "pumpkin", "potato"};
    private String wordToGuess;


    public Dictionary() {
        wordToGuess = wordList[new Random().nextInt(wordList.length+1)];
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    private void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public void start() {
        char[] wordChars = wordToGuess.toCharArray();
        System.out.println("Я загадал слово на английском, введи его:");
        while(true) {
            Scanner input = new Scanner(System.in);
            //Проверяем не пустое ли поле ввода
            if (input.hasNextLine()) {
                String word = input.nextLine();
                //Проверяем не угадано ли слово
                boolean isEqual = word.trim().equals(wordToGuess);
                if (isEqual) { //Выводим сообщение о том что слово угадано, выходим из цикла
                    System.out.println("Слово угадано верно");
                    break;
                }  else {
                    int[] rightIndexes = new int[wordChars.length];
                    char[] wordChars2 = word.toCharArray();

                    //Переменная указывающая что хотя бы один символ совпал
                    boolean found = false;

                    //Проверяем какие символы совпадают в массивах
                    for (int i = 0; i < wordChars.length; i++) {
                        if  (i<wordChars2.length && wordChars[i] == wordChars2[i]) {
                            rightIndexes[i] = i;
                            found = true;
                        } else {
                            rightIndexes[i] = -1;
                        }
                    }

                    //Если совпал выводим совпавшие
                    if (!found) {
                        for (int i=0; i<15; i++) {
                            System.out.print("✶");
                        }
                    } else {    //Выводим 15 звёзд если не совпал не один
                        System.out.println("Слово угадано не верно, но я дам подсказку, покажу какие буквы " +
                                "занимают свои места");
                        for (int i = 0; i<rightIndexes.length; i++) {
                            if (rightIndexes[i] == -1) {
                                System.out.print("✶");
                            } else {
                                System.out.print(wordChars[i]);
                            }
                        }
                        for (int i= rightIndexes.length; i<15; i++) {
                            System.out.print("✶");
                        }
                    }
                }
            } else {
                System.out.println("Поле ввода пустое");
            }
            System.out.println("\nПопробуй ещё раз");
        }
    }
}
