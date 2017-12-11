import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 0));
        coordinates.add(new Coordinate(4, 0));
        coordinates.add(new Coordinate(4, 4));
        coordinates.add(new Coordinate(0, 4));
        AreaCalculator calculator = new AreaCalculator();
        InputHandler inputHandler = new InputHandler(coordinates);
        double area = calculator.calculateArea(inputHandler.getXCoordinates(coordinates), inputHandler.getYCoordinates(coordinates), inputHandler.getNumberOfPoints());
        System.out.println(area);
    }
}
