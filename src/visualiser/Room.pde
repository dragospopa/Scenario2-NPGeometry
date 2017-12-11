public class Room implements Drawable {
  private ArrayList<Coord> vertices;
  
  // Unit size in pixels
  private int unit = 50;
  
  public Room(ArrayList<Float> coordinates) {
    vertices = new ArrayList<Coord>();
    for (int i = 1; i < coordinates.size(); i += 2) {
      vertices.add(new Coord(coordinates.get(i - 1), coordinates.get(i)));
    }
  }
  
  public void setUnitSize(float size) {
    unit = (int) size;
  }
  
  public void draw(Canvas parent) {
    stroke(0);
    for (int i = 1; i < vertices.size(); i++) {
      line(vertices.get(i-1).x * unit,
          vertices.get(i-1).y * unit,
          vertices.get(i).x * unit,
          vertices.get(i).y * unit);
      println("Drawing line from (" + vertices.get(i-1).x + "," +
      vertices.get(i-1).y + ") to (" + vertices.get(i).x + "," +
      vertices.get(i).y + ").");
    }
    line(vertices.get(vertices.size() - 1).x * unit,
         vertices.get(vertices.size() - 1).y * unit,
         vertices.get(0).x * unit,
         vertices.get(0).y * unit);
  }
  
}