package Animals;

public abstract class Animal{
    private static Integer counter = 0;
    public abstract void run(Integer distance);
    public abstract void swim(Integer distance);

    public static Integer getCounter() {
        return counter;
    }

    protected static void incrementCounter () {
        counter++;
    }
}


