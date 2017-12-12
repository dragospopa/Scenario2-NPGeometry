import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        IlyaFileReader ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();

        // stuff that was already here
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
