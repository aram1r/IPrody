import exceptions.ArrayDataException;
import exceptions.ArraySizeException;

public class Main {
    public static void main(String[] args) {
        try {
            String[][] array = new String[4][4];
            for ( int i = 0; i < array.length; i++ ) {
                for ( int j = 0; j < array[i].length; j++ ) {
                    array[i][j] = 1 + "";
                }
            }
//            array[2] = null;
            array[3][3] = "ab";
            System.out.println(ArrayValueCalculator.doCalc(array));
        } catch (NullPointerException e) {
            System.out.println("array is null");
        } catch (ArraySizeException e) {
            System.out.println("Массив неверного размера");
        } catch (ArrayDataException e) {
            System.out.println("Не удалось обработать текст в ячейке [" + e.getI() + "][" + e.getJ() + "]");
        }
    }
}