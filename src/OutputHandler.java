import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputHandler {
    BufferedWriter output;

    OutputHandler() {
        try {
            this.output = new BufferedWriter(new FileWriter("output.txt"));
        } catch (Exception e) {
            System.out.println("Something went wrong creating the output file");
        }
    }

    void formatHeader() throws IOException {
        output.write("seville\n");
        output.write("p1n1h08ouvfgepn6gupnqdblut\n");
    }

    String generatePointSequence(Shape shape) {
        ArrayList<IlyaCoordinate> points =shape.vertices;
        StringBuilder pointSequence = new StringBuilder();
        for (IlyaCoordinate coordinate:points) {
            String temp=" (" + coordinate.getX() + ", " + coordinate.getY() +"),";
            pointSequence.append(temp);
        }
        pointSequence.append(";");
        return pointSequence.toString();
    }

    String generateLocationList(ArrayList<Shape> shapes) {
        StringBuilder locationList = new StringBuilder();
        for (Shape shape: shapes) {
            locationList.append(generatePointSequence(shape));
        }
        return locationList.toString();
    }

    String formatForProblem(int problemNumber,ArrayList<Shape> shapes) {
        return problemNumber+":"+generateLocationList(shapes);
    }

    String formatForAll(ArrayList<ArrayList<Shape>> shapesList) {
        StringBuilder output = new StringBuilder();
        for (int i=0;i<shapesList.size();i++) {
            output.append(formatForProblem(i,shapesList.get(i)));
            output.append("\n");
        }
        return output.toString();
    }

    void generateOutputFile(ArrayList<ArrayList<Shape>> shapesList) throws IOException {
        formatHeader();
        this.output.write(formatForAll(shapesList));
    }
}

