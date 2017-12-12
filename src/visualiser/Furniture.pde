public class Furniture implements Drawable {
  private PShape m_shape;
  private float pos_x, pos_y;
  private float m_scale = 1.0;
  
  public Furniture(ArrayList<Float> vertices, int cost) {
    m_shape = createPolygon(vertices);
    m_shape.setFill(#e26ca9);
    pos_x = random(WINDOW_WIDTH);
    pos_y = random(WINDOW_HEIGHT);
  }
  
  public void show() {
    m_shape.setVisible(true);
    pos_x = random(WINDOW_WIDTH);
    pos_y = random(WINDOW_HEIGHT);
  }
  
  public void setScale(float s) {
    m_scale = s;
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
    fill(#e26ca9);
    scale(m_scale);
    shapeMode(CENTER);
    shape(m_shape, pos_x / m_scale, pos_y / m_scale);
    scale(1 / m_scale);
  }
}