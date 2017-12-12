import java.util.List;

public class AreaCalculator {

    public static double calculateArea(List<Double> xCoordinates, List<Double> yCoordinates, Integer numberOfPoints) {
        double area = 0;
        int j = numberOfPoints - 1;

        for (int i=0; i<numberOfPoints; i++) {
            area = area +  (xCoordinates.get(j)+xCoordinates.get(i)) * (yCoordinates.get(i)-yCoordinates.get(j));
            j = i;  //j is previous vertex to i
        }
        return area/2;
    }
}
