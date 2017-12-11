import java.util.List;

public class Screen {
  
  private ArrayList<Drawable> objects = new ArrayList<Drawable>();
  
  public void addItem(Drawable item) {
    objects.add(item);
  }
  
  public void draw() {
    for (Drawable object : objects) {
      object.draw();
    }
  }
}