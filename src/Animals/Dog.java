package Animals;

public class Dog extends Animal {

    public Dog() {
        incrementCounter();
    }

    @Override
    public void run(Integer distance) {
        if (distance > 500) distance=500;
        System.out.println("Dog ran " + distance);
    }
    @Override
    public void swim(Integer distance) {
        if (distance > 10) distance=10;
        System.out.println("Dog swam " + distance);
    }
}
