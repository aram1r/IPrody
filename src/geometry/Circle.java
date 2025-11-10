package geometry;

public class Circle extends FigureClass {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        area = Math.PI * Math.pow(radius, 2);
    }
}
