import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IlyaFileReader ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();
        Coordinate coordinateArr[] = new Coordinate[4];
        Coordinate coordinateArr2[] = new Coordinate[4];
        Coordinate coordinateArr3[] = new Coordinate[7];
        Polygon polygons[] = new Polygon[2];
        coordinateArr[0] = new Coordinate(0,0);
        coordinateArr[1] = new Coordinate(0,4);
        coordinateArr[2] = new Coordinate(2,2);
        coordinateArr[3] = new Coordinate(0,0);
        polygons[0] = new Polygon(new LinearRing(new CoordinateArraySequence(coordinateArr), new GeometryFactory()), null, new GeometryFactory());

        coordinateArr2[0] = new Coordinate(2,2);
        coordinateArr2[1] = new Coordinate(4,4);
        coordinateArr2[2] = new Coordinate(4,0);
        coordinateArr2[3] = new Coordinate(2,2);
        polygons[1] = new Polygon(new LinearRing(new CoordinateArraySequence(coordinateArr2), new GeometryFactory()), null, new GeometryFactory());
        MultiPolygon temp = new MultiPolygon(polygons, new GeometryFactory());

        coordinateArr3[0] = new Coordinate(0,0);
        coordinateArr3[1] = new Coordinate(0,4);
        coordinateArr3[2] = new Coordinate(2,2);
        coordinateArr3[3] = new Coordinate(4,0);
        coordinateArr3[4] = new Coordinate(4,4);
        coordinateArr3[5] = new Coordinate(2,2);
        coordinateArr3[6] = new Coordinate(0,0);

        CoordinateSequence sequence= new CoordinateArraySequence(coordinateArr3);
        LinearRing ring = new LinearRing(sequence, new GeometryFactory());



        System.out.println(ring.convexHull().getArea());
    }
}
