import java.util.List;

public static final int WINDOW_WIDTH = 1600;
public static final int WINDOW_HEIGHT = 1600;

// no. of pixels per unit of drawing space
public static final int PPU = 90;

void settings() {
  size(WINDOW_WIDTH, WINDOW_HEIGHT);
}

Room room;
Canvas roomCanvas;

void setup() {
  background(255);
  smooth();
  noStroke();
  
  roomCanvas = new Canvas(3*PPU, 3*PPU, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
  
  ArrayList<Float> coordinates = new ArrayList<Float>();
  String[] lines = loadStrings("output.txt");
  for (String line : lines) {
    String filteredLine = line.replaceAll("[(),]", "");
    for (String arg : filteredLine.split(" ")) { //<>//
      float number = Float.parseFloat(arg);
      coordinates.add(number);
    }
  }
  room = new Room(roomCanvas, coordinates);
  roomCanvas.addItem(room);
}

void draw() {
  stroke(0);
  strokeWeight(4);
  //roomCanvas.drawBorder();
  roomCanvas.draw();
}