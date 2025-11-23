package MyList;

public class DoubleQueueList extends QueueList{
    public DoubleQueueList ( int length ) {
        super(length);
    }

    public void addToHead ( Object element ) {
        if (array != null) {
            if (array[0] == null) {
                array[0] = element;
            } else {
                boolean flag = false;
                for (int i = 1; i < array.length; i++) {
                    if  (array[i] == null) {
                        array[i] = element;
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    Object[] temp = new Object[array.length + 1];
                    temp[0] = element;
                    for (int i = 0; i < array.length; i++) {
                        temp[i+1] = array[i];
                    }
                    array = temp;
                }
            }
        }
    }

    public void addToTail ( Object element ) {
        if (array !=null) {
            if (array[0]!=null){
                if (!isFull()) {
                    for (int i = 0; i < array.length; i++){
                        if (array[i]==null){
                            array[i]=element;
                            break;
                        }
                    }
                } else {
                    Object[] temp = new Object[array.length+1];
                    for (int i = 0; i < array.length; i++){
                        temp[i] = array[i];
                    }
                    temp[temp.length-1]=element;
                    array = temp;
                }
            } else {
                array[0] = element;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public Object getHead() {
        Object temp = array[0];
        removeFirstElement();
        return temp;
    }

    public Object getTail() {
        Object temp = new Object();
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            if (array[0]==null) {
                temp = null;
                flag = true;
            } else if (array[i]==null) {
                temp = array[i-1];
                flag = true;
            }
        }
        if (!flag) {
            temp = array[array.length-1];
            removeLastElement();
        }
        return temp;
    }

    private void removeLastElement() {
        Object[] temp = new Object[array.length-1];
        if (array!=null && array.length>0) {
            if (array[array.length-1]!=null) {
                for (int i = 0; i<array.length-1; i++) {
                    temp[i] = array[i];
                }
                array = temp;
            }
        }
    }

    private boolean isFull() {
        for (int i = 0; i < array.length-1; i++) {
            if (array[i]==null) {
                return false;
            }
        }
        return true;
    };
}
