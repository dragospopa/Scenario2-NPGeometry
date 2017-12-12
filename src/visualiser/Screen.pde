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
  
  
}