import coffeeshot.CoffeeOrderBoard;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        coffeeOrderBoard.addOrder("Ilya");
        coffeeOrderBoard.addOrder("Anya");
        coffeeOrderBoard.addOrder("Sasha");
        coffeeOrderBoard.addOrder("Alex");
        coffeeOrderBoard.addOrder("Ivan");
        coffeeOrderBoard.addOrder("Max");
        coffeeOrderBoard.addOrder("Yulia");
        coffeeOrderBoard.addOrder("Masha");
        coffeeOrderBoard.addOrder("Anastacia");
        coffeeOrderBoard.addOrder("Sergei");
        coffeeOrderBoard.addOrder("Dima");

        coffeeOrderBoard.deliver(4);

        coffeeOrderBoard.deliver();

        coffeeOrderBoard.draw();
    }
}