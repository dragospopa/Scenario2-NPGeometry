import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {


        // TODO: move all the reading and parsing to other placez, obviously
        String fileName = "problems.rfp";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] aux = line.split("#");

                // parse room

                // we don't really need those first 3 characters ever
                aux[0] = aux[0].substring(3).trim();

                // get each pair of coordinates
                String[] roomCoords = aux[0].split(Pattern.quote(")"));
                ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
                for (String coordsPair : roomCoords) {

                    // hacking the pattern
                    if (coordsPair.charAt(0) == '(') {
                        coordsPair = coordsPair.substring(1);
                    } else coordsPair = coordsPair.substring(3);

                    // separate each coord of a pair
                    String[] coords = coordsPair.split(",");
                    Double x_coord = Double.parseDouble(coords[0].trim());
                    Double y_coord = Double.parseDouble(coords[1].trim());

                    // start building stuff up
                    Coordinate roomCoord = new Coordinate(x_coord, y_coord);
                    coordinates.add(roomCoord);
                }
                Room room = new Room(coordinates);

                // parse furnitures
                String[] furnitures = aux[1].split(";");
                ArrayList<Furniture> decoration = new ArrayList<Furniture>();

                // get each furniture string
                for (String strFurniture : furnitures) {
                    // separate unitCost from vertices
                    String[] furnitureParts = strFurniture.split(":");
                    String unitCostStr = furnitureParts[0].trim();

                    // separate each pair of coordinates
                    String[] furnitureStrCoords = furnitureParts[1].split(Pattern.quote(")"));
                    ArrayList<Coordinate> furnitureCoordinates = new ArrayList<Coordinate>();
                    for (String coordsPair : furnitureStrCoords) {

                        // hacking the pattern
                        if (coordsPair.charAt(0) == '(') {
                            coordsPair = coordsPair.substring(1);
                        } else coordsPair = coordsPair.substring(3);
                        coordsPair = coordsPair.trim();

                        // separate each coord of a pair
                        String[] coords = coordsPair.split(",");
                        Double x_coord = Double.parseDouble(coords[0].trim());
                        Double y_coord = Double.parseDouble(coords[1].trim());

                        // start building stuff up
                        Coordinate singleFurnitureCoord = new Coordinate(x_coord, y_coord);
                        furnitureCoordinates.add(singleFurnitureCoord);
                    }
                    Furniture furniture = new Furniture(Integer.parseInt(unitCostStr), furnitureCoordinates);
                    decoration.add(furniture);
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }


        // stuff that was already here
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 0));
        coordinates.add(new Coordinate(4, 0));
        coordinates.add(new Coordinate(4, 4));
        coordinates.add(new Coordinate(0, 4));
        AreaCalculator calculator = new AreaCalculator();
        InputHandler inputHandler = new InputHandler(coordinates);
        double area = calculator.calculateArea(inputHandler.getXCoordinates(coordinates), inputHandler.getYCoordinates(coordinates), inputHandler.getNumberOfPoints());
        System.out.println(area);
    }
}
