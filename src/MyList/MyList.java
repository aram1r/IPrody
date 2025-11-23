package MyList;

public class MyList extends AbstractListClass {

    public MyList(int length) {
        super(length);
    }

    public void add (Object o){
        //Проверяем не предельной ли длины массив, если да - копируем его в новый увеличенной длины
        if ( array[array.length-1] != null ) {
            Object[] temp = new Object[(int) (array.length*1.6)];

            int j = 0;
            for (; j<array.length; j++) {
                temp[j] = array[j];
            }
            array = temp;
        }
        int index = 0;
        //Ищем индекс последнего элемента в массиве
        for ( ; index < array.length; index++ ){
            if (array[index] == null){
                break;
            }
        }
        //Вставляем на место последнего индекс элемент
        array[index] = o;
    }
}
