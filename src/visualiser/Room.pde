public class Room implements Drawable {
  private PShape roomShape;
  private float m_scale = 100.0f;
  
  public Room(ArrayList<Float> roomVertices) {
    roomShape = createPolygon(roomVertices);
    println("roomshape is " + roomShape.width + "x" + roomShape.height);
  }
  
  public void move(int x, int y) {
    roomShape.translate(x,y);
  }
  
  public void show() {
    roomShape.setVisible(true);
  }
  
  public void hide() {
    roomShape.setVisible(false);
  }
  
  public void draw() {
    stroke(0);
    scale(m_scale);
    shapeMode(CENTER);
    shape(roomShape, 8, 6);
    scale(1 / m_scale);
  }
  
  public int getWidth() {
    return round(roomShape.width);
  }
  
  public int getHeight() {
    return round(roomShape.height);
  }
  
}