import competition.Competition;
import geometry.Circle;
import geometry.FigureClass;
import geometry.Square;
import geometry.Triangle;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FigureClass[] figures = new FigureClass[5];
        figures[0] = new Circle(5);
        figures[1] = new Square(5);
        figures[2] = new Square(10);
        figures[3] = new Triangle(3, 4);
        figures[4] = new Circle(8.0);

        double totalArea = 0;

        for (FigureClass figure : figures) {
            figure.calculateArea();
            totalArea += figure.getArea();
        }

        System.out.println(totalArea);

        Competition.start();
    }
}