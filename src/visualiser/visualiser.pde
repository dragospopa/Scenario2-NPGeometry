import java.util.List;

public static final int WINDOW_WIDTH = 1600;
public static final int WINDOW_HEIGHT = 1600;

// no. of pixels per unit of drawing space
public static final int PPU = 90;

void settings() {
  size(WINDOW_WIDTH, WINDOW_HEIGHT);
}

ScreenManager screenManager;
MenuBar buttonMenu;
ScaleSlider roomSlider;

void setup() {
  smooth();
  noStroke();
  
  screenManager = new ScreenManager();
  buttonMenu = new MenuBar(WINDOW_WIDTH / 14, round(WINDOW_HEIGHT * 0.85));
  roomSlider = new ScaleSlider(WINDOW_WIDTH * 0.9, WINDOW_HEIGHT / 2, WINDOW_WIDTH / 30, WINDOW_HEIGHT / 2);
  
  int num_rooms = 1;
  String[] lines = loadStrings("input.txt");
  for (String line : lines) {
    
    Screen screen = new Screen();
    
    String filtered = line.replaceAll("[(),]", "");
    String[] temp = filtered.split("# ");
    String roomCoords = temp[0];
    
    int start_index = roomCoords.indexOf(":") + 2;
    roomCoords = roomCoords.substring(start_index);
    
    ArrayList<Float> roomVertices = new ArrayList<Float>();
    for (String arg : roomCoords.split(" ")) {
      roomVertices.add(Float.parseFloat(arg));
    }
    screen.addRoom(new Room(roomVertices));
    
    String itemCoords = temp[1];
    
    for (String arg: itemCoords.split(";")) {
      int colon_index = arg.indexOf(":");
      int cost = Integer.parseInt(arg.substring(0, colon_index));
      String contents = arg.substring(colon_index + 1);
      ArrayList<Float> objectVertices = new ArrayList<Float>(); //<>//
      for (String arg2: contents.split(" ")) { //<>//
        objectVertices.add(Float.parseFloat(arg2));
      }
      screen.addFurniture(new Furniture(objectVertices, cost));
    }
    screenManager.addScreen(screen); //<>//
    buttonMenu.addMenuItem(new Button(num_rooms++));
  }
  screenManager.switchToScreen(1);
}

void draw() {
  background(#77939b);
  textSize(65);
  textAlign(CENTER);
  fill(255);
  text("Furnishing Crab Caves - a visualisation", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 20);
  stroke(0);
  strokeWeight(3);
  buttonMenu.draw();
  roomSlider.draw();
  screenManager.drawActiveScreen();
}