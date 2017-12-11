public class Button implements Drawable {
  public PShape circle;
  
  public Button() {
    ellipseMode(CENTER);
    circle = createShape(ELLIPSE, 0, 0, WINDOW_WIDTH / 12, WINDOW_HEIGHT / 12); 
    circle.setFill(0);
  }
  
  public void draw() {
    
  }
  
  public void show() {
  
  }
  
  public void hide() {
  
  }
}