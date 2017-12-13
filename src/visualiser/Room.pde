public class Room implements Drawable {
  private PShape roomShape;
  private float m_scale = 100.0f;
  private float x_pos, y_pos;
  
  public Room(ArrayList<Float> roomVertices) {
    roomShape = createPolygon(roomVertices);
    setCenterPos();
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
    //println("room has dimensions " + roomShape.width + "x" + roomShape.height);
    stroke(0);
    shapeMode(CORNERS);
    pushMatrix();
    setCenterPos();
    translate(x_pos, y_pos);
    scale(m_scale);
    shape(roomShape, 0, 0);
    translate(-x_pos, -y_pos);
    popMatrix();
  }
  
  private void setCenterPos() {
    x_pos = (WINDOW_WIDTH / 2) - (m_scale * roomShape.width / 2);
    y_pos = (WINDOW_HEIGHT / 2) - (m_scale * roomShape.height / 2);
  }
  
  public int getWidth() {
    return round(roomShape.width);
  }
  
  public int getHeight() {
    return round(roomShape.height);
  }
  
  public float getX() {
    return x_pos;
  }
  
  public float getY() {
    return y_pos;
  }
}