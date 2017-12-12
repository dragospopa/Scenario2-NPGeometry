import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomaszczernuszenko on 11/12/2017.
 */
public abstract class Shape {
    ArrayList<Coordinate> vertices;
    int unitCost;
    double realCost;

    // for furniture
    public Shape(int unitCost, ArrayList<Coordinate> vertices) {
        this.vertices = vertices;
        this.unitCost = unitCost;
        this.realCost = this.area() * unitCost;
    }

    // for the roomz
    public Shape(ArrayList<Coordinate> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Coordinate> rotate(double degrees){
        return vertices;
    }

    public ArrayList<Coordinate> convexHull(){
        return vertices;
    }

    public ArrayList<Coordinate> rectangleOver(){
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

}
