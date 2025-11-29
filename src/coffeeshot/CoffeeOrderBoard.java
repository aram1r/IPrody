package coffeeshot;

import java.util.ArrayList;

public class CoffeeOrderBoard{
    private ArrayList<Order> orders;
    private int lastId;
    private int firstId;

    public CoffeeOrderBoard() {
        orders = new ArrayList<>();
        lastId = 0;
        firstId = 0;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(String name) {
        orders.add(new Order(lastId+1, name));
        lastId++;
    }

    public Order deliver() {
        Order result = null;
        if (orders.isEmpty()) {
            return null;
        }
        if (orders.get(firstId)!=null) {
            result = orders.get(firstId);
            orders.set(firstId, null);
            firstId++;
        }
        return result;
    }

    public Order deliver (int id) {
        Order result = null;
        if (orders.isEmpty()) {
            return null;
        }
        if (orders.get(id)!=null) {
            result = orders.get(id);
            orders.set(id, null);
        }
        return result;
    }

    public void draw () {
        System.out.println("Num | Name");
        for (Order order : orders) {
            if (order!=null) {
                System.out.println(order.getId() + " | " + order.getCustomerName());
            }
        }
    }
}
