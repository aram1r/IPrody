import exceptions.ArrayDataException;
import exceptions.ArraySizeException;

public class ArrayValueCalculator{

    public static int doCalc (String[][] array) {
        int sum = 0;
        int a =-1, b = -1;
        try {
            if (array==null) {
                throw new NullPointerException();
            }
            for (int i = 0; i < 4; i++ ) {
                if (array[i]==null) {
                    throw new NullPointerException();
                }
                if (array[i].length!=4) {
                    throw new ArraySizeException("Array Size Error");
                }
                a = i;
                for (int j = 0; j < 4; j++ ) {
                    if (array[i][j]==null) {
                        throw new NullPointerException();
                    }
                    if (array[i].length!=4) {
                        throw new ArraySizeException("Array Size Error");
                    }
                    b = j;
                    try {
                            sum += Integer.parseInt(array[i][j]);
                        } catch (NumberFormatException e) {
                            throw new ArrayDataException("Number Format Error");
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("array[" + a + "] is null");
        } catch (ArraySizeException e) {
            System.out.println("Массив неверного размера");
        } catch (ArrayDataException e) {
            System.out.println("Не удалось обработать текст в ячейке [" + a + "][" + b + "]");
        }
        return sum;
    }
}
