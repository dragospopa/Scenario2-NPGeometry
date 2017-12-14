import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        TaskSplitter splitter = new TaskSplitter();
        splitter.getInput();
        splitter.splitTasks();
    }
}
