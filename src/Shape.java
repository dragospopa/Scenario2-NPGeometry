import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomaszczernuszenko on 11/12/2017.
 */
public abstract class Shape {
    ArrayList<IlyaCoordinate> vertices;
    int unitCost;
    double realCost;

    // for furniture
    public Shape(int unitCost, ArrayList<IlyaCoordinate> vertices) {
        this.vertices = vertices;
        this.unitCost = unitCost;
        this.realCost = this.area() * unitCost;
    }

    // for the roomz
    public Shape(ArrayList<IlyaCoordinate> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<IlyaCoordinate> rotate(double degrees){
        ArrayList<IlyaCoordinate> rotatedVertices = new ArrayList<>();
        for (IlyaCoordinate coordinate:vertices) {
            rotatedVertices.add(new IlyaCoordinate((coordinate.getX())*Math.cos(degrees)-(coordinate.getY())*Math.sin(coordinate.getY()), (coordinate.getX())*Math.sin(degrees)+(coordinate.getY())*Math.cos(degrees)));
        }
        return rotatedVertices;
    }

    public Geometry convexHull(){
        return getPolygon().convexHull();
    }

    public ArrayList<IlyaCoordinate> rectangleOver(){
        return vertices;
    }

    public double area(){
        List <Double> x_coord = new ArrayList<Double>();
        List <Double> y_coord = new ArrayList<Double>();
        for (int i=0;i<this.vertices.size();i++){
            x_coord.add(this.vertices.get(i).getX());
            y_coord.add(this.vertices.get(i).getY());
        }

        // ffs we dont really need to pass the size, this aint C anymore
        return AreaCalculator.calculateArea(x_coord, y_coord, this.vertices.size());
    }

    public Polygon getPolygon(){
        Coordinate coordinates[] = new Coordinate[vertices.size()+1];
        for(int i=0; i<vertices.size(); i++){
            Coordinate coordinate = new Coordinate(vertices.get(i).getX(), vertices.get(i).getY());
            coordinates[i] = coordinate;
        }
        coordinates[coordinates.length-1] = new Coordinate(vertices.get(0).getX(), vertices.get(0).getY());
        CoordinateSequence coordinateSequence = new CoordinateArraySequence(coordinates);
        return new Polygon(new LinearRing(coordinateSequence, new GeometryFactory()), null, new GeometryFactory());
    }

}
