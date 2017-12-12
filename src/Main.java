import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        IlyaFileReader ilyaFileReader = new IlyaFileReader();
        ilyaFileReader.readFile();

        Polygon p = IlyaFileReader.decorations.get(0).get(0);
        System.out.println(p);
    }
}
