import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IlyaFileReader ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();

        CaseSolver caseSolver = new CaseSolver(ilyaFileReader.rooms.get(0),ilyaFileReader.decorations.get(0));
        caseSolver.solve();
    }
}
