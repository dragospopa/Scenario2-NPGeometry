import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private List<Double> xCoordinates=new ArrayList<>();
    private List<Double> yCoordinates= new ArrayList<>();
    private int numberOfPoints = 0;

    public InputHandler(List<Coordinate> coordinates) {
        this.xCoordinates = getXCoordinates(coordinates);
        this.yCoordinates = getYCoordinates(coordinates);
        this.numberOfPoints = coordinates.size();
    }

    private void getCoordinateLists(List<Coordinate> coordinates) {
            for (Coordinate coordinate: coordinates) {
                xCoordinates.add(coordinate.getX());
                yCoordinates.add(coordinate.getY());
        }
    }

    public List<Double> getXCoordinates(List<Coordinate> coordinates) {
        if (xCoordinates.isEmpty()) {
            getCoordinateLists(coordinates);
        }
        return xCoordinates;
    }

    public List<Double> getYCoordinates(List<Coordinate> coordinates) {
        if (yCoordinates.isEmpty()) {
            getCoordinateLists(coordinates);
        }
        return yCoordinates;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
