package geometry;

public class Square extends FigureClass {

    private final double a;


    public Square(double a) {
        this.a = a;
    }

    @Override
    public void calculateArea() {
        area = a * a;
    }
}
