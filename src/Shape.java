import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomaszczernuszenko on 11/12/2017.
 */
public abstract class Shape implements Comparable{
    ArrayList<IlyaCoordinate> vertices;
    ArrayList<IlyaCoordinate> tempVertices;
    int unitCost;
    double realCost;

    double minX, maxX, minY, maxY;

    // for furniture
    public Shape(int unitCost, ArrayList<IlyaCoordinate> vertices) {
        this(vertices);
        this.unitCost = unitCost;
        this.realCost = this.area() * unitCost;
        tempVertices = new ArrayList<>();
    }

    // for the roomz
    public Shape(ArrayList<IlyaCoordinate> vertices) {
        this.vertices = vertices;
        setExtrema();
    }

    public ArrayList<IlyaCoordinate> rotate(double degrees){
        return vertices;
    }

    public ArrayList<IlyaCoordinate> convexHull(){
        return vertices;
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

    public double value(){
        return unitCost/area()/area();
    }

    //it might be sorting the wrong way
    @Override
    public int compareTo(Object o) {
        if(this.value()>((Shape)o).value()){
            return 1;
        } else if (this.value()>((Shape)o).value()) {
            return -1;
        }
        return 0;
    }

    public void translateToStartFrom(IlyaCoordinate coordinates){
        tempVertices.clear();
        for (IlyaCoordinate c: vertices) {
            tempVertices.add(new IlyaCoordinate(c.getX() + coordinates.getX(), c.getY() + coordinates.getY()));
        }
    }

    public ArrayList<IlyaCoordinate> getTempVertices() {
        return tempVertices;
    }

    public Polygon getTempPolygon(){
        Coordinate coordinates[] = new Coordinate[tempVertices.size()+1];
        for(int i=0; i<tempVertices.size(); i++){
            Coordinate coordinate = new Coordinate(tempVertices.get(i).getX(), tempVertices.get(i).getY());
            coordinates[i] = coordinate;
        }
        coordinates[coordinates.length-1] = new Coordinate(tempVertices.get(0).getX(), tempVertices.get(0).getY());
        CoordinateSequence coordinateSequence = new CoordinateArraySequence(coordinates);
        return new Polygon(new LinearRing(coordinateSequence, new GeometryFactory()), null, new GeometryFactory());
    }

    public void setExtrema(){
        minX = Double.MAX_VALUE;
        minY = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        maxY = Double.MIN_VALUE;
        for (IlyaCoordinate c: vertices) {
            if(c.getX()<minX)
                minX = c.getX();
            if(c.getX()>maxX)
                maxX = c.getX();
            if(c.getY()<minY)
                minY = c.getY();
            if(c.getY()>maxY)
                maxY = c.getY();
        }
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }
}