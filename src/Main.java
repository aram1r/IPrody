import MyList.ConnectedList;
import MyList.DoubleQueueList;

public class Main {
    public static void main(String[] args) {
        ConnectedList connectedList = new ConnectedList(10);
        System.out.println(connectedList.getLength());
        for (int i = 0; i < 15; i++) {
            connectedList.add(1);
        }
        System.out.println(connectedList.getLength());
        System.out.println(connectedList.get(5));
//        System.out.println(connectedList.get(20));

        DoubleQueueList queueList = new DoubleQueueList(10);
        for (int i = 0; i < 10; i++) {
            queueList.addToHead(i);
        }
        queueList.addToHead(25);
        System.out.println("Длина массива: " + queueList.getLength());
        System.out.println(queueList.getHead());
        System.out.println("Длина массива " + queueList.getLength());
        System.out.println(queueList.getHead() + " теперь длина массива " + queueList.getLength());

        System.out.println(queueList.getTail() + " теперь длина массива " + queueList.getLength());
        System.out.println(queueList.getTail() + " теперь длина массива " + queueList.getLength());
        System.out.println(queueList.getTail() + " теперь длина массива " + queueList.getLength());
    }
}