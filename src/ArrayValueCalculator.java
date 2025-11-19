import exceptions.ArrayDataException;
import exceptions.ArraySizeException;

public class ArrayValueCalculator{

    public static int doCalc (String[][] array) throws ArrayDataException, ArraySizeException, NullPointerException {
        int sum = 0;
        int i = 0, j = 0;
        if ( array==null ) {
            throw new NullPointerException();
        }
        for ( ; i < 4; i++ ) {
            if ( array[i]==null ) {
                throw new NullPointerException();
            }
            if ( array[i].length!=4 ) {
                throw new ArraySizeException("Array Size Error");
            }
            for ( ; j < 4; j++ ) {
                if ( array[i][j]==null ) {
                    throw new NullPointerException();
                }
                if ( array[i].length!=4 ) {
                    throw new ArraySizeException("Array Size Error");
                }
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new ArrayDataException(i, j);
                }
            }
            j = 0;
        }
        return sum;
    }
}
