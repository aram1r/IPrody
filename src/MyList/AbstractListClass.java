package MyList;

public abstract class AbstractListClass {
    protected Object[] array;

    public AbstractListClass (int length) {
        array = new Object[length];
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public int getLength() {
        return array.length;
    }
}
