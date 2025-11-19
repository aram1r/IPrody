public class ConnectedList{
    Object[] array;
    int length;

    public ConnectedList(int length) {
        this.length = length;
        array = new Object[length];
    }

    public void insert(Object o){
        //Проверяем не предельной ли длины массив, если да - копируем его в новый увеличенной длины
        if (array[length-1] != null) {
            Object[] temp = new Object[(int) (length*1.6)];

            int j = 0;
            for (; j<length; j++) {
                temp[j] = array[j];
            }
            length = temp.length;
            array = temp;
        }
        int index = 0;
        //Ищем индекс последнего элемента в массиве
        for (; index < length; index++){
            if (array[index] == null){
                break;
            }
        }
        //Вставляем на место последнего индекс элемент
        array[index] = o;
    }

    public Object get(int index) {
        if (index < length && index >= 0) {
            return array[index];
        } else if (array[index] == null) {
            throw new NullPointerException("Index: " + index + " is empty");
        } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
    }

    public int getLength() {
        return length;
    };
}
