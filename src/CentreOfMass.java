import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CentreOfMass {
    IlyaCoordinate centreOfMass;

    IlyaCoordinate getAverageCoordinates(ArrayList<IlyaCoordinate> coordinates) {
        double sumX=0;
        double sumY=0;
        int len = coordinates.size();
        for (IlyaCoordinate coordinate:coordinates) {
            sumX += coordinate.getX();
            sumY += coordinate.getY();
        }

        return new IlyaCoordinate(sumX/len,sumY/len);
    }

    double findOptimumRotation(Shape shape, int precision) {
        IlyaCoordinate currentMin = new IlyaCoordinate(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
        IlyaCoordinate tempCoordinate;
        double opRotation = 0;
        double interval = 360/precision;
        for (int rotate=0;rotate<precision;rotate++) {
            double rotation = rotate*interval;
            tempCoordinate = getAverageCoordinates(shape.rotate(rotation));
            if (tempCoordinate.getY()<currentMin.getY()) {
                currentMin = tempCoordinate;
                opRotation = rotation;
            }
        }
        return opRotation;
    }


}
