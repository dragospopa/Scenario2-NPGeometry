import org.locationtech.jts.geom.Polygon;

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
        for (Furniture f: decorations) {
            f.gravityRotate(1000);
        }
        sortDecorations(); //suspition that the sort does not work...
        for (Furniture f: decorations) {
            IlyaCoordinate bestDropPoint;

            generateRandomValidDropPoints(f, 1000, 1000);

            bestDropPoint = hypotheticalLowestCentreOfGravity(f, );

            if(bestDropPoint != null)
                applyGravity(f, bestDropPoint);
        }
        System.out.println("Done. Coverage: " + getCoverage());
        OutputHandler handler = new OutputHandler();
        result = handler.formatForProblem(order, placedItems);
        if(getCoverage()>30){
            System.out.println(result);
        }
    }

    private void sortDecorations(){
        Collections.sort(decorations);
    }

    private void generateRandomValidDropPoints(Furniture f, int numberOfAttempts, double precision){
        for (int i = 0; i < numberOfAttempts; i++) {
            IlyaCoordinate coordinate = new IlyaCoordinate(room.getMinX(), room.getMaxX(), room.getMinY(), room.getMaxY());
            f.translateToStartFrom(coordinate);

            double interval = 360/precision;
            for (int j = 0; j < precision; j++) {
                double rotationAngle = interval*i;
                f.rotateVertices(rotationAngle, f.getTempVertices());
                if(doesElementFit(f.getPolygon(f.getRotatedCoordinates()))){
                    f.commitRotatedToMain();
                    placedItems.add(f);
                    return;
                }
            }
        }
    }

    private boolean doesElementFit(Polygon p){
        try {
            if (!room.getPolygon(room.vertices).contains(p)) //This line throws a NPE not sure where it originates from
                return false;
            for (Furniture addedF : placedItems) {
                if (p.intersects(addedF.getPolygon(addedF.getTempVertices())))
                    return false;
            }
        } catch (Exception e){
            System.out.println();
            return false;
        }
        return true;
    }

    private void applyGravity(Furniture f, IlyaCoordinate dropPoint){
        f.translateToStartFrom(dropPoint);
        f.commitTempToMain();
        placedItems.add(f);
    }

    private double hypotheticalLowestCentreOfGravity(Furniture f, IlyaCoordinate dropPoint){
        double shift = 0;

        while (doesElementFit(f.getPolygon(f.getTempVertices()))) {
            f.translateToStartFrom(new IlyaCoordinate(dropPoint.getX(), dropPoint.getY() + shift));
            shift -= 0.01;
        }
        if (shift < 0)
            shift += 0.01;
        return shift;
    }

    private IlyaCoordinate hypotheticalLowestCentreOfGravity(Furniture f, ArrayList<IlyaCoordinate> dropPoints){
        double min = Double.MAX_VALUE, x;
        IlyaCoordinate minPoint = null;
        for (IlyaCoordinate dropPoint : dropPoints) {
            x = hypotheticalLowestCentreOfGravity(f, dropPoint);
            if(x < min) {
                min = x;
                minPoint = dropPoint;
            }
        }
        return minPoint;
    }

    public double getCoverage() {
        double roomArea = room.getPolygon(room.vertices).getArea();
        double polygonArea = 0;
        for (Furniture furniture: placedItems) {
            polygonArea+=furniture.getPolygon(furniture.vertices).getArea();
        }
        return (polygonArea/roomArea)*100;
    }

    public String extractResult(){
        return result;
    }
}
