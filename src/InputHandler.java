import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    private List<Double> xCoordinates=new ArrayList<>();
    private List<Double> yCoordinates= new ArrayList<>();
    private int numberOfPoints = 0;

    public InputHandler(List<IlyaCoordinate> ilyaCoordinates) {
        this.xCoordinates = getXCoordinates(ilyaCoordinates);
        this.yCoordinates = getYCoordinates(ilyaCoordinates);
        this.numberOfPoints = ilyaCoordinates.size();
    }

    private void getCoordinateLists(List<IlyaCoordinate> ilyaCoordinates) {
            for (IlyaCoordinate ilyaCoordinate : ilyaCoordinates) {
                xCoordinates.add(ilyaCoordinate.getX());
                yCoordinates.add(ilyaCoordinate.getY());
        }
    }

    public List<Double> getXCoordinates(List<IlyaCoordinate> ilyaCoordinates) {
        if (xCoordinates.isEmpty()) {
            getCoordinateLists(ilyaCoordinates);
        }
        return xCoordinates;
    }

    public List<Double> getYCoordinates(List<IlyaCoordinate> ilyaCoordinates) {
        if (yCoordinates.isEmpty()) {
            getCoordinateLists(ilyaCoordinates);
        }
        return yCoordinates;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
