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
  
  public void draw() {
    stroke(0);
    shapeMode(CENTER);
    shape(m_shape, 8, 6);
  }
}