package fruits;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final ArrayList<T> fruits;
    private final Class<T> clazz;
    private Double weight;

    public Box(Class<T> clazz) {
        this.clazz = clazz;
        this.fruits = new ArrayList<>();
        weight = 0d;
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void addFruit(Fruit fruit) {
        if (clazz.isInstance(fruit)) {
            fruits.add(clazz.cast(fruit));
            weight += fruit.getWeight();
        }
    }

    public Double getWeight() {
        return weight;
    }

    public Boolean compare(Box<? extends Fruit> box) {
        return this.weight>box.getWeight();
    }

    public void transfer(Box<? super T> box) {
        for (T fruit : fruits) {
            box.addFruit(fruit);
        }
        fruits.clear();
        weight = 0d;
    }
}
