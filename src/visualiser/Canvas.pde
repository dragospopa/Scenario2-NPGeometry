import java.util.List;

public class Canvas {
  
  public int x_offset, y_offset;
  private int width, height;
  private ArrayList<Drawable> objects;
  
  public Canvas(int x, int y, int width, int height) {
    objects = new ArrayList<Drawable>();
    x_offset = x;
    y_offset = y;
    this.width = width;
    this.height = height;
  }
  
  public void drawBorder() {
    drawBorder(3, 0);
  }
  
  public void drawBorder(int thickness, color colour) {
    stroke(colour);
    strokeWeight(thickness);
    line(x_offset, y_offset, x_offset + width, y_offset);
    line(x_offset, y_offset, x_offset, y_offset + height);
    line(x_offset + width, y_offset, x_offset + width, y_offset + height);
    line(x_offset, y_offset + height, x_offset + width, y_offset + height);
  }
  
  public void addItem(Drawable item) {
    objects.add(item);
  }
  
  public void draw() {
    for (Drawable object : objects) {
      object.draw();
    }
  }
  
}