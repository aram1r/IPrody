package fruits;

public class Fruit {
    protected Float weight;

    public Fruit() {

    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "weight=" + weight +
                "} " + this.getClass().getSimpleName();
    }
}
