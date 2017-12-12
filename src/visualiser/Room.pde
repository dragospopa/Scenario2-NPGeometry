public class Room implements Drawable {
  private PShape roomShape;
  private ArrayList<PShape> items = new ArrayList<PShape>();
  
  private float m_scale = 100.0f;
  
  public Room(ArrayList<Float> roomVertices) {
    roomShape = createPolygon(roomVertices);
  }
  
  private PShape createPolygon(ArrayList<Float> coordinates) {
    PShape new_shape = createShape();
    new_shape.beginShape();
    new_shape.stroke(0);
    new_shape.strokeWeight(0.15);
    for (int i = 1; i < coordinates.size(); i += 2) {
      new_shape.vertex(coordinates.get(i - 1), coordinates.get(i));
    }     
    new_shape.endShape(CLOSE);
    return new_shape; 
  }
  
  public void addObject(ArrayList<Float> vertices) {
    items.add(createPolygon(vertices));
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
  
}