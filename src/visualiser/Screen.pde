import java.util.List;

public enum DrawMode {
  PROBLEM, SOLUTION
}

public class Screen {
  
  private ArrayList<Furniture> furniture = new ArrayList<Furniture>();
  private Room room;
  private DrawMode currentMode = DrawMode.PROBLEM;
  
  public void addRoom(Room room) {
    this.room = room;
  }  
  
  public void addFurniture(Furniture f) {
    furniture.add(f);
  }
  
  public void clearFurniture() {
    currentMode = DrawMode.SOLUTION;
    furniture.clear();
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
    if (currentMode == DrawMode.PROBLEM) {
      for (Furniture item : furniture) {
        item.draw();
      }
      room.draw();
    } else {
      room.draw();
      for (Furniture item : furniture) {
        item.draw();
      }
    }
    
  }
  
  public float getRoomX() {
    return room.getX();
  }
  
  public float getRoomY() {
    return room.getY();
  }
  
}