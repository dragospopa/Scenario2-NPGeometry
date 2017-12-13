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

    public CaseSolver(Room room, ArrayList<Furniture> decorations) {
        this.room = room;
        this.decorations = decorations;
        this.placedItems = new ArrayList<>();
    }

    public void solve(){
        for (Furniture f: decorations) {
            f.gravityRotate(1000);
        }
        sortDecorations(); //suspition that the sort does not work...
        for (Furniture f: decorations) {
            IlyaCoordinate bestDropPoint;

            bestDropPoint = hypotheticalLowestCentreOfGravity(f, generateRandomValidDropPoints(f, 100));

            if(bestDropPoint != null)
                applyGravity(f, bestDropPoint);
        }
        System.out.println("Done");
    }

    private void sortDecorations(){
        Collections.sort(decorations);
    }

    private ArrayList<IlyaCoordinate> generateRandomValidDropPoints(Furniture f, int numberOfAttempts){
        ArrayList<IlyaCoordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < numberOfAttempts; i++) {
            IlyaCoordinate coordinate = new IlyaCoordinate(room.getMinX(), room.getMaxX(), room.getMinY(), room.getMaxY());
            f.translateToStartFrom(coordinate);
            if(doesElementFit(f.getPolygon(f.tempVertices)))
                coordinates.add(coordinate);
        }

    return coordinates;
    }

    private boolean doesElementFit(Polygon p){
        if (!room.getPolygon(room.vertices).covers(p))
            return false;
        for (Furniture addedF: placedItems) {
            if(p.intersects(addedF.getPolygon(addedF.getTempVertices())))
                return false;
        }
        return true;
    }

    private void applyGravity(Furniture f, IlyaCoordinate dropPoint){
        f.translateToStartFrom(dropPoint);
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
}
