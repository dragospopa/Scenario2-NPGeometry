public class Room implements Drawable {
  private PShape roomShape;
  private float m_scale = 100.0f;
  private float x_pos, y_pos;
  
  public Room(ArrayList<Float> roomVertices) {
    roomShape = createPolygon(roomVertices);
    x_pos = WINDOW_WIDTH / 2 / m_scale;
    y_pos = WINDOW_HEIGHT / 2 / m_scale;
    //println("roomshape is " + roomShape.width + "x" + roomShape.height);
  }
  
  public void setScale(float s) {
    m_scale = s;
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
    pushMatrix();
    translate(x_pos, y_pos);
    scale(m_scale);
    translate(-x_pos, -y_pos);
    setCenterPos();
    shapeMode(CENTER);
    shape(roomShape, x_pos, y_pos);
    popMatrix();
  }
  
  private void setCenterPos() {
    x_pos = WINDOW_WIDTH / 2;
    y_pos = WINDOW_HEIGHT / 2;
  }
  
  public int getWidth() {
    return round(roomShape.width);
  }
  
  public int getHeight() {
    return round(roomShape.height);
  }
  
}