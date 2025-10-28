public class Main {
    public static void main(String[] args) {
        Integer count = findSymbolOccurance("Сколько символов с", 'с');
        System.out.println(count);

        String source = "съешь ещё этих мягких французских булок";
        String target = "булки";
        System.out.println(findWordPosition(source, target));

        System.out.println(stringReverse("Hello"));

        System.out.println(isPalindrome("ABBa"));

        Dictionary dictionary = new Dictionary();
        System.out.println(dictionary.getWordToGuess());
        dictionary.start();
    }

    private static Integer findSymbolOccurance (String string, char сh) {
        String temp = string.toLowerCase();
        int count = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == сh) {
                count++;
            }
        }
        return count;
    }

    private static Integer findWordPosition (String source, String target) {
        if (source.contains(target)) {
            String[] words = source.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(target)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static String stringReverse (String source) {
        return new StringBuilder(source).reverse().toString();
    }

    private static boolean isPalindrome(String source) {
        return new StringBuilder(source).reverse().toString().equalsIgnoreCase(source);
    }
}