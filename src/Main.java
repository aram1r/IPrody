import com.example.app.coffee.order.CoffeeOrderBoard;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();
        coffeeOrderBoard.addOrder("James");
        coffeeOrderBoard.addOrder("John");
        coffeeOrderBoard.addOrder("Peter");
        coffeeOrderBoard.addOrder("Mary");
        coffeeOrderBoard.addOrder("David");

        coffeeOrderBoard.deliver(1);
        coffeeOrderBoard.deliver(0);
        coffeeOrderBoard.deliver(14);
    }
}