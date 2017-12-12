import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tomaszczernuszenko on 12/12/2017.
 */

//This class is responsible for solving a given test case. I don't want to have everything in Main, and also this will make it easier to split the tasks between Threads
public class CaseSolver {
    Room room;
    ArrayList<Furniture> decorations;

    public CaseSolver(Room room, ArrayList<Furniture> decorations) {
        this.room = room;
        this.decorations = decorations;
    }

    public void solve(){
        for (Furniture f: decorations) {
            f.convexHull(); //make sure this edits the instance data of the shape
            //f.gravityRotate();
        }
        sortDecorations();
        for (Furniture f: decorations) {
            double minimalCentre=Double.MAX_VALUE;
            double currentMinCentre;
            IlyaCoordinate currentDropPoint, bestDropPoint = null;

            for (int i = 0; i < 10; i++) {
                currentDropPoint = generateRandomValidDropPoint();
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

    private IlyaCoordinate generateRandomValidDropPoint(){

        //This will require a tone of work
        return null;
    }

    private void applyGravity(Furniture f, IlyaCoordinate dropPoint){

    }

    private double hypotheticalLowestCentreOfGravity(Furniture f, IlyaCoordinate dropPoint){
        return -1;
    }
}
