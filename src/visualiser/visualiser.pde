import java.util.List;

public static final int WINDOW_WIDTH = 1600;
public static final int WINDOW_HEIGHT = 1600;

// no. of pixels per unit of drawing space
public static final int PPU = 90;

void settings() {
  size(WINDOW_WIDTH, WINDOW_HEIGHT);
}

Room room;
Button button;
MenuBar buttonMenu;

void setup() {
  background(#77939b);
  textSize(65);
  textAlign(CENTER);
  text("Furnishing Crab Caves - a visualisation", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 12);
  smooth();
  noStroke();
  
  //roomCanvas = new Canvas(3*PPU, 3*PPU, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
  
  
  String[] lines = loadStrings("input.txt");
  for (String line : lines) {
    
    Screen screen = new Screen();
    
    String filtered = line.replaceAll("[(),]", "");
    String[] temp = filtered.split("#");
    String roomCoords = temp[0];
    
    int start_index = roomCoords.indexOf(":") + 2;
    roomCoords = roomCoords.substring(start_index);
    
    ArrayList<Float> roomVertices = new ArrayList<Float>();
    for (String arg : roomCoords.split(" ")) {
      roomVertices.add(Float.parseFloat(arg));
    }
    screen.addRoom(new Room(roomVertices));
    
    String itemCoords = temp[1];
    
    int colon_index = itemCoords.indexOf(":");
    int cost = Integer.parseInt(itemCoords.substring(0, colon_index));
    itemCoords = itemCoords.substring(colon_index + 1);
    for (String arg: itemCoords.split(";")) {
      ArrayList<Float> objectVertices = new ArrayList<Float>();
      for (String arg2: arg.split(" ")) {
        objectVertices.add(Float.parseFloat(arg2));
      }
      screen.addFurniture(new Furniture(objectVertices, cost));
    } //<>//
  }
  
  //room = new Room(coordinates);
  //buttonMenu = new MenuBar(WINDOW_WIDTH / 12, round(WINDOW_HEIGHT * 0.85));
  //buttonMenu.addMenuItem(new Button(1));
  //buttonMenu.addMenuItem(new Button(2));
}

void draw() {
  stroke(0);
  strokeWeight(3);
  //room.draw();
  //buttonMenu.draw();
}