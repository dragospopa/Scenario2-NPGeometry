public class MenuBar implements Drawable {
  
  int pos_x, pos_y;
  ArrayList<Drawable> contents = new ArrayList<Drawable>();
  
  public MenuBar(int x, int y) {
    pos_x = x;
    pos_y = y;
  }
  
  public void addMenuItem(Drawable item) {
    item.move(pos_x, pos_y);
    contents.add(item);
  }
  
  public void move(int x, int y) {
    for (Drawable item : contents) {
      item.move(x, y);
    }
    pos_x += x;
    pos_y += y;
  }
  
  public void show() {
    for (Drawable item : contents) {
      item.show();
    }
  }
  
  public void hide() {
    for (Drawable item : contents) {
      item.hide();
    }
  }
  
  public void draw() {
    for (Drawable item : contents) {
      item.draw();
    }
  }
}