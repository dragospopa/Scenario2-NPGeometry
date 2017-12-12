import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.ArrayList;

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
        tempVertices = this.vertices;
        this.unitCost = unitCost;
        this.realCost = this.area() * unitCost;
    }

    // for the roomz
    public Shape(ArrayList<IlyaCoordinate> vertices) {
        this.vertices = vertices;
        setExtrema();
    }

    public IlyaCoordinate centreOfMass(){
        double sum_x = 0, sum_y = 0;
        for (IlyaCoordinate coords : this.vertices){
            sum_x += coords.getX();
            sum_y += coords.getY();
        }
        return new IlyaCoordinate(sum_x/this.vertices.size(),sum_y/this.vertices.size());
    }

    public void rotate(double degrees){
        tempVertices = new ArrayList<>();
        for (IlyaCoordinate coords : this.vertices){
            tempVertices.add(new IlyaCoordinate(coords.getX() * Math.cos(degrees) - coords.getY() * Math.sin(degrees), coords.getX() * Math.sin(degrees) + coords.getY() * Math.cos(degrees)));
        }
    }

    public double area(){
        return getPolygon(this.vertices).convexHull().getArea();
    }

    public Polygon getPolygon(ArrayList<IlyaCoordinate> v){
        Coordinate coordinates[] = new Coordinate[v.size()+1];
        for(int i=0; i<v.size(); i++){
            Coordinate coordinate = new Coordinate(v.get(i).getX(), v.get(i).getY());
            coordinates[i] = coordinate;
        }
        coordinates[coordinates.length-1] = new Coordinate(v.get(0).getX(), v.get(0).getY());
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