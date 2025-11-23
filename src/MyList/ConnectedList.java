package MyList;

public class ConnectedList extends MyList{

    public ConnectedList(int length) {
        super(length);
    }

    public Object get(int index) {
        if ( index < array.length && index >= 0 ) {
            return array[index];
        } else if ( array[index] == null ) {
            throw new NullPointerException("Index: " + index + " is empty");
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + array.length);
        }
    }

    public int getLength() {
        return array.length;
    };
}
