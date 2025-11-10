package geometry;

public class Triangle extends FigureClass {
    private double a;
    private double h;

    public Triangle(double a, double h) {
        this.a = a;
        this.h = h;
    }

    @Override
    public void calculateArea() {
        area = a*h*0.5;
    }
}
