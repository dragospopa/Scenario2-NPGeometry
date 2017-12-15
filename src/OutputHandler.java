import javafx.beans.binding.StringBinding;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputHandler {
    BufferedWriter output;

    public OutputHandler() {
        try {
            this.output = new BufferedWriter(new FileWriter("output.txt"));
        } catch (Exception e) {
            System.out.println("Something went wrong creating the output file");
        }
    }

    public void formatHeader() throws IOException {
        output.write("seville\n");
        output.write("p1n1h08ouvfgepn6gupnqdblut\n");
    }

    public  String generatePointSequence(Shape shape) {
        ArrayList<IlyaCoordinate> points = shape.vertices; //need to decide if we print vertices or tempVertices
        StringBuilder pointSequence = new StringBuilder();
        for (IlyaCoordinate coordinate:points) {
            pointSequence.append("#" + shape.unitCost + ": ");
            String temp=" (" + coordinate.getX() + ", " + coordinate.getY() +"),";
            pointSequence.append(temp);
        }
        String output = pointSequence.substring(0, pointSequence.length()-1);
        output += ";";
        return output;
    }
    
    public String generateLocationList(ArrayList<Furniture> shapes) {
        StringBuilder locationList = new StringBuilder();
        for (Shape shape: shapes) {
            locationList.append(generatePointSequence(shape));
        }
        return locationList.toString();
    }
    
    public String formatForProblem(int problemNumber,ArrayList<Furniture> shapes) {
        return problemNumber+":"+generateLocationList(shapes);
    }
    
    public String formatForAll(ArrayList<ArrayList<Furniture>> shapesList) {
        StringBuilder output = new StringBuilder();
        for (int i=0;i<shapesList.size();i++) {
            output.append(formatForProblem(i,shapesList.get(i)));
            output.append("\n");
        }
        return output.toString();
    }
    
//    public void generateOutputFile(ArrayList<ArrayList<Furniture>> shapesList) throws IOException {
//        formatHeader();
//        this.output.write(formatForAll(shapesList));
//    }

    public void generateOutputFile(ArrayList<String> outputs) throws IOException {
        formatHeader();
        StringBuilder builder = new StringBuilder();
        for (String output : outputs) {
            if(output.charAt(output.length()-1)==';'){
                output = output.substring(0, output.length()-1);
            }
            builder.append(output);
            builder.append("\n");
        }
        this.output.write(builder.toString());
        this.output.close();
    }
}

