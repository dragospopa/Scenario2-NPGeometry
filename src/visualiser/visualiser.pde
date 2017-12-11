import java.util.List;

public static final int WINDOW_WIDTH = 1000;
public static final int WINDOW_HEIGHT = 1000;

public class Coord {
  public final float x;
  public final float y;
  public Coord(float x, float y) {
    this.x = x;
    this.y = y;
  }
}

void settings() {
  size(WINDOW_WIDTH, WINDOW_HEIGHT);
}

Room room;
Canvas roomCanvas;

void setup() {
  background(255);
  smooth();
  noStroke();
  
  roomCanvas = new Canvas(50, 50, 500, 500);
  
  ArrayList<Float> coordinates = new ArrayList<Float>();
  float max_coord = 0, min_coord = 0;
  String[] lines = loadStrings("output.txt");
  for (String line : lines) {
    String filteredLine = line.replaceAll("[(),]", "");
    for (String arg : filteredLine.split(" ")) { //<>//
      float number = Float.parseFloat(arg);
      coordinates.add(number);
    }
  }
  room = new Room(coordinates);
  roomCanvas.addItem(room);
}

void draw() {
  stroke(0);
  //roomCanvas.drawBorder();
  roomCanvas.draw();
}