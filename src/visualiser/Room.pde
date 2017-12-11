public class Room implements Drawable {
  private ArrayList<Coord> vertices;
  private int width, height;
  private final Canvas parent;
  
  // Unit size in pixels
  private int unit = 50;
  
  public Room(Canvas parent, ArrayList<Float> coordinates) {
    this.parent = parent;
    float min_x = 0, min_y = 0, max_x = 0, max_y = 0;
    vertices = new ArrayList<Coord>();
    for (int i = 1; i < coordinates.size(); i += 2) {
      float new_x = coordinates.get(i - 1);
      float new_y = coordinates.get(i);
      vertices.add(new Coord(new_x, new_y));
      min_x = min(new_x, min_x);
      min_y = min(new_y, min_y);
      max_x = max(new_x, max_x);
      max_y = max(new_y, max_y);
    }
    width = round(abs(max_x - min_x));
    height = round(abs(max_y - min_y));
    println("This room is " + width + "x" + height + " units.");
     
  }
  
    public void setUnitSize(float size) {
      unit = (int) size;
    }
  
  public void draw() {
    stroke(0);
    for (int i = 1; i < vertices.size(); i++) {
      line((vertices.get(i-1).x * unit) + parent.x_offset,
          (vertices.get(i-1).y * unit) + parent.y_offset,
          (vertices.get(i).x * unit) + parent.x_offset,
          (vertices.get(i).y * unit) + parent.y_offset);
      //println("Drawing line from (" + vertices.get(i-1).x + "," +
      //vertices.get(i-1).y + ") to (" + vertices.get(i).x + "," +
      //vertices.get(i).y + ").");
    }
    line((vertices.get(vertices.size() - 1).x * unit) + parent.x_offset,
         (vertices.get(vertices.size() - 1).y * unit) + parent.y_offset,
         (vertices.get(0).x * unit) + parent.x_offset,
         (vertices.get(0).y * unit) + parent.y_offset);
  }
  
}