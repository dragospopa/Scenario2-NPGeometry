public class Room implements Drawable {
  private PShape roomShape;
  
  // Unit size in pixels
  private int m_scale = 100;
  
  public Room(ArrayList<Float> coordinates) {
    roomShape = createShape();
    roomShape.beginShape();
    roomShape.stroke(0);
    roomShape.strokeWeight(0.15);
    for (int i = 1; i < coordinates.size(); i += 2) {
      roomShape.vertex(coordinates.get(i - 1), coordinates.get(i));
    }     
    roomShape.endShape(CLOSE);
    for (int i = 0; i < roomShape.getVertexCount(); i++) {
      println(roomShape.getVertex(i));
    }
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
    shape(roomShape, 8, 8);
  }
  
}