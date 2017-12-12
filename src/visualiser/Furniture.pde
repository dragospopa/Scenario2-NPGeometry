public class Furniture implements Drawable {
  private PShape m_shape;
  
  public Furniture(ArrayList<Float> vertices, int cost) {
    m_shape = createPolygon(vertices);
  }
  
  public void show() {
    m_shape.setVisible(true);
  }
  
  public void hide() {
    m_shape.setVisible(false);
  }
  
  public void move(int x, int y) {
    m_shape.translate(x, y);
  }
  
  public int getWidth() {
    return round(m_shape.width);
  }
  
  public int getHeight() {
    return round(m_shape.height);
  }
  
  public void draw() {
    stroke(0);
    shapeMode(CENTER);
    shape(m_shape, 8, 6);
  }
}