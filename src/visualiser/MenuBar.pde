public class MenuBar implements Drawable {
  
  private final int width, height;
  // no. of pixels between adjacent menu items
  private final int SPACE = 10;
  
  private int pos_x, pos_y;
  private int x_offset = 0, y_offset = 0;
  private ArrayList<Drawable> contents = new ArrayList<Drawable>();
  
  public MenuBar(int x, int y) {
    pos_x = x;
    pos_y = y;
    width = WINDOW_WIDTH;
    height = WINDOW_HEIGHT + pos_y;
  }
  
  public void addMenuItem(Drawable item) {
    if (x_offset + pos_x + item.getWidth() >= width) {
      x_offset = 0;
      y_offset += item.getHeight() + SPACE;
    }
    item.move(pos_x + x_offset, pos_y + y_offset);
    x_offset += item.getWidth() + SPACE;
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
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
}