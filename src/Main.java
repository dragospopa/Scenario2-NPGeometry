import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IlyaFileReader ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();

        // stuff that was already here
        ArrayList<IlyaCoordinate> ilyaCoordinates = new ArrayList<>();
        ilyaCoordinates.add(new IlyaCoordinate(0, 0));
        ilyaCoordinates.add(new IlyaCoordinate(4, 0));
        ilyaCoordinates.add(new IlyaCoordinate(4, 4));
        ilyaCoordinates.add(new IlyaCoordinate(0, 4));
        AreaCalculator calculator = new AreaCalculator();
        InputHandler inputHandler = new InputHandler(ilyaCoordinates);
        double area = calculator.calculateArea(inputHandler.getXCoordinates(ilyaCoordinates), inputHandler.getYCoordinates(ilyaCoordinates), inputHandler.getNumberOfPoints());
        System.out.println(area);

        Polygon p = IlyaFileReader.decorationss.get(0).get(0).getPolygon();
        System.out.println(p);
    }
}
