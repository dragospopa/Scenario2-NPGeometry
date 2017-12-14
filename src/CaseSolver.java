import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tomaszczernuszenko on 12/12/2017.
 */

//This class is responsible for solving a given test case. I don't want to have everything in Main, and also this will make it easier to split the tasks between Threads
public class CaseSolver {
    Room room;
    ArrayList<Furniture> decorations;
    ArrayList<Furniture> placedItems;

    String result;

    public CaseSolver(Room room, ArrayList<Furniture> decorations) {
        this.room = room;
        this.decorations = decorations;
        this.placedItems = new ArrayList<>();
    }

    public void solve(int order){
        if(order!=11){
            result=" ";
            return;
        }

        sortDecorations();//suspition that the sort does not work...
        System.out.println("Number of elements to process:" + decorations.size());
        boolean outcome;
        for (int i = 0; i < decorations.size(); i++) {
            Furniture f = decorations.get(i);
            System.out.print("Working on element: " + i + "; Unit cost: " + f.getRealCost() + "; ");

            outcome = generateRandomValidDropPoints(f, 100, 10*1000);
            System.out.println(outcome + "; Coverage: " + getCoverage());
            if(outcome)
                System.out.println("Location: " +placedItems.get(placedItems.size()-1).getVertices());
        }
        System.out.println("Done. Coverage: " + getCoverage() + "; Cost: " + getTotalCost());
        OutputHandler handler = new OutputHandler();
        result = handler.formatForProblem(order, placedItems);
        if(getCoverage()>30){
            System.out.println(result);
        }
    }

    private void sortDecorations(){
        //Collections.sort(decorations);

        for (int i = 0; i < decorations.size(); i++) {
            for (int j = i+1; j < decorations.size(); j++) {
                if(decorations.get(i).compareTo(decorations.get(j))>0){
                    Furniture f = decorations.get(i);
                    decorations.set(i, decorations.get(j));
                    decorations.set(j, f);
                }
            }
        }
        for (int i = 0; i <= decorations.size()/2; i++) {
            Furniture f = decorations.get(i);
            decorations.set(i, decorations.get(decorations.size()-1-i));
            decorations.set(decorations.size()-1-i, f);
        }
    }

    private boolean generateRandomValidDropPoints(Furniture f, int numberOfAttempts, double precision){
        int i=0;

        for (IlyaCoordinate c:room.getVertices()) {

            while(i<numberOfAttempts) {
                IlyaCoordinate coordinate = new IlyaCoordinate(c.getX()-1, c.getX()+1, c.getY()-1, c.getY()+1);
                if(!checkIfCoordinateInRoom(coordinate))
                    continue;

                f.translateToStartFrom(coordinate);
                if(tryRotatingAndCommit(f, precision))
                    return true;

                i++;
            }
        }
        return false;
    }

    private boolean checkIfCoordinateInRoom(IlyaCoordinate coordinate){
        Coordinate coordinate1 = new Coordinate(coordinate.getX(), coordinate.getY());

        CoordinateSequence coordinateSequence = new CoordinateArraySequence(new Coordinate[]{coordinate1});
        Point point = new Point(coordinateSequence, new GeometryFactory());
        return room.getPolygon(room.getVertices()).covers(point);
    }

    private boolean tryRotatingAndCommit(Furniture f, double precision){
        double interval = 360/precision;
        for (int j = 0; j < precision; j++) {
            double rotationAngle = interval*j;
            f.rotateVertices(rotationAngle, f.getTempVertices());
            if(doesElementFit(f.getPolygon(f.getRotatedCoordinates()))){
                f.commitRotatedToMain();
                placedItems.add(f);
                return true;
            }
        }
        return false;
    }

    private boolean doesElementFit(Polygon p){
        if (!room.getPolygon(room.getVertices()).contains(p))
            return false;
        for (Furniture addedF : placedItems) {
            if (p.overlaps(addedF.getPolygon(addedF.getTempVertices())))
                return false;
        }
        return true;
    }

    public double getCoverage() {
        double roomArea = room.getPolygon(room.getVertices()).getArea();
        double polygonArea = 0;
        for (Furniture furniture: placedItems) {
            polygonArea+=furniture.getPolygon(furniture.getVertices()).getArea();
        }
        return (polygonArea/roomArea)*100;
    }

    public double getTotalCost() {
        double sum = 0;
        for (Furniture furniture: placedItems) {
            sum += furniture.getRealCost();
        }
        return sum;
    }

    public String extractResult(){
        return result;
    }
}
