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

    String generatePointSequence(ArrayList<IlyaCoordinate> points) {
        StringBuilder pointSequence = new StringBuilder();
        for (IlyaCoordinate coordinate:points) {
            String temp=" (" + coordinate.getX() + ", " + coordinate.getY() +"),";
            pointSequence.append(temp);
        }
        pointSequence.append(";");
        return pointSequence.toString();
    }

    String generateLocationList()
}

