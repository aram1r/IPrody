package MyList;

public class QueueList extends AbstractListClass {

    public QueueList(int queueLength) {
        super(queueLength);
    }

    public Object getFirstElement() {
        if (array.length >= 1 && array[0] != null) {
            Object obj = array[0];
            removeFirstElement();
            return obj;
        }
        return null;
    }

    protected void removeFirstElement(){
        Object[] temp = new Object[array.length - 1];
        for ( int i = 1; i <array.length; i++ ) {
            temp[i-1] = array[i];
        }
        array = temp;
    }
}
