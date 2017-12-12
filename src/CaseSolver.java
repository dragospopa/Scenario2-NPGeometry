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
            //f.gravityRotate();
        }
        sortDecorations();
        for (Furniture f: decorations) {
            double minimalCentre=Double.MAX_VALUE;
            double currentMinCentre;
            IlyaCoordinate currentDropPoint = null, bestDropPoint = null;

            for (int i = 0; i < 10; i++) {
                //currentDropPoint = generateRandomValidDropPoint();
                currentMinCentre = hypotheticalLowestCentreOfGravity(f, currentDropPoint);
                if(currentMinCentre < minimalCentre){
                    minimalCentre = currentMinCentre;
                    bestDropPoint = currentDropPoint;
                }
            }
            if(bestDropPoint != null)
                applyGravity(f, bestDropPoint);
        }
    }

    private void sortDecorations(){
        Collections.sort(decorations);
    }

    private IlyaCoordinate generateRandomValidDropPoint(Furniture f){
        IlyaCoordinate coordinate = null;
        //we might want to generate a few different valid points and see which of them work best
        do {
            coordinate = new IlyaCoordinate(room.getMinX(), room.getMaxX(), room.getMinY(), room.getMaxY());
            f.translateToStartFrom(coordinate);
        } while (!doesElementFit(f.getPolygon(f.tempVertices)));

        return coordinate;
    }

    private boolean doesElementFit(Polygon p){
        if (!room.getPolygon(room.vertices).covers(p))
            return false;
        for (Furniture addedF: placedItems) {
            if(p.intersects(addedF.getPolygon()))
                return false;
        }
        return true;
    }

    private void applyGravity(Furniture f, IlyaCoordinate dropPoint){

    }

    private double hypotheticalLowestCentreOfGravity(Furniture f, IlyaCoordinate dropPoint){

        return -1;
    }
}
