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
  
  public void setRoomScale(float s) {
    room.setScale(s);
  }
  
  public void draw() {
    room.draw();
    //for (Furniture item : furniture) {
    //  item.draw();
    //}
  }
  
}