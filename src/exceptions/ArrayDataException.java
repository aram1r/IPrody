package exceptions;

public class ArrayDataException extends NumberFormatException {
    int i, j;
    public ArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public ArrayDataException(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
}
