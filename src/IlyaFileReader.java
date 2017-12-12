import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by tomaszczernuszenko on 12/12/2017.
 */
public class IlyaFileReader {

    public static ArrayList<Room> rooms;

    public static ArrayList<ArrayList<Furniture>> decorations;

    public IlyaFileReader() {
        decorations = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public void readFile(){
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

                // get each pair of ilyaCoordinates
                String[] roomCoords = aux[0].split(Pattern.quote(")"));
                ArrayList<IlyaCoordinate> ilyaCoordinates = new ArrayList<>();
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
                    IlyaCoordinate roomCoord = new IlyaCoordinate(x_coord, y_coord);
                    ilyaCoordinates.add(roomCoord);
                }
                Room room = new Room(ilyaCoordinates);

                // parse furnitures
                String[] furnitures = aux[1].split(";");
                ArrayList<Furniture> decoration = new ArrayList<>();

                // get each furniture string
                for (String strFurniture : furnitures) {
                    // separate unitCost from vertices
                    String[] furnitureParts = strFurniture.split(":");
                    String unitCostStr = furnitureParts[0].trim();

                    // separate each pair of ilyaCoordinates
                    String[] furnitureStrCoords = furnitureParts[1].split(Pattern.quote(")"));
                    ArrayList<IlyaCoordinate> furnitureIlyaCoordinates = new ArrayList<IlyaCoordinate>();
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
                        IlyaCoordinate singleFurnitureCoord = new IlyaCoordinate(x_coord, y_coord);
                        furnitureIlyaCoordinates.add(singleFurnitureCoord);
                    }
                    Furniture furniture = new Furniture(Integer.parseInt(unitCostStr), furnitureIlyaCoordinates);
                    decoration.add(furniture);
                }
                rooms.add(room);
                decorations.add(decoration);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            System.exit(0);
        }
        System.out.println("File Read. Number of rooms: " +rooms.size() + "; Number of decoration sets: " + decorations.size());
    }
}
