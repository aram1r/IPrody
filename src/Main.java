public class Main {
    public static void main(String[] args) {
        String[][] array = new String[4][4];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 1 + "";
            }
        }
        array[2] = null;
        array[3][3] = "ab";
        System.out.println(ArrayValueCalculator.doCalc(array));
    }
}