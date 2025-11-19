public class Main {
    public static void main(String[] args) {
        ConnectedList connectedList = new ConnectedList(10);
        System.out.println(connectedList.getLength());
        for (int i = 0; i < 15; i++) {
            connectedList.insert(1);
        }
        System.out.println(connectedList.getLength());
        System.out.println(connectedList.get(5));
        System.out.println(connectedList.get(20));
    }
}