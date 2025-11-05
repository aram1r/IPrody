package Animals;

public class Cat extends Animal {

    public Cat() {
        incrementCounter();
    }

    @Override
    public void swim(Integer distance) {
        System.out.println("Cat can't swim");
    }
    @Override
    public void run(Integer distance) {
        if (distance>200) distance=200;
        System.out.println("Cat ran" + distance);
    }
}
