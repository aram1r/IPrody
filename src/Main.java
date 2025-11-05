import Animals.Animal;
import Animals.Cat;
import Animals.Dog;

public class Main {
    public static void main(String[] args) {


        Dog dog = new Dog();

        System.out.println(Animal.getCounter());

        Cat cat = new Cat();

        System.out.println(Animal.getCounter());

        dog.run(700);
        cat.swim(300);
    }
}