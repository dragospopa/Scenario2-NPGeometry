import java.util.List;

public class Screen {
  
  private ArrayList<Furniture> furniture = new ArrayList<Furniture>();
  private Room room;
  
  public void addRoom(Room room) {
    this.room = room;
  }  
  
  public void addFurniture(Furniture f) {
    furniture.add(f);
  }
  
  public void hide() {
    room.hide();
    for (Furniture item : furniture) {
      item.hide();
    }
  }
  
  public void show() {
    room.show();
    for (Furniture item : furniture) {
      item.show();
    }
  }
  
  public void setScale(float s) {
    room.setScale(s);
    for (Furniture item : furniture) {
      item.setScale(s);
    }
  }
  
  public void draw() {
    for (Furniture item : furniture) {
      item.draw();
    }
    room.draw();
  }
  
}