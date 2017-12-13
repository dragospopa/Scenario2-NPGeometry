import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IlyaFileReader ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();

//        // stuff that was already here
//        ArrayList<IlyaCoordinate> ilyaCoordinates = new ArrayList<>();
//        ilyaCoordinates.add(new IlyaCoordinate(0, 0));
//        ilyaCoordinates.add(new IlyaCoordinate(2, 2));
//        ilyaCoordinates.add(new IlyaCoordinate(0, 4));
//
//        Polygon p = IlyaFileReader.decorationss.get(0).get(0).getPolygon(ilyaCoordinates);
//        Furniture f = new Furniture(4,ilyaCoordinates);
//        f.gravityRotate(1000);

//        for (int i = 0 ; i<f.tempVertices.size();i++) {
//            System.out.println(f.tempVertices.get(i).getX() +" "+ f.tempVertices.get(i).getY() );
//        }

        CaseSolver caseSolver = new CaseSolver(ilyaFileReader.rooms.get(0),ilyaFileReader.decorationss.get(0));
        caseSolver.solve();
    }
}
