import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IlyaFileReader ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();
        ArrayList<IlyaCoordinate> testList = new ArrayList<>();
        testList.add(new IlyaCoordinate(0,0));
        testList.add(new IlyaCoordinate(2,2));
        testList.add(new IlyaCoordinate(0,4));
        Shape testShape = new Furniture(3, testList);
        CentreOfMass centre = new CentreOfMass();
        System.out.println(centre.findOptimumRotation(testShape,1000));
    }
}
