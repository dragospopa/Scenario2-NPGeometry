import java.util.List;

public enum DrawMode {
  PROBLEM(0), SOLUTION(1);
  
  private int value;
  private DrawMode(int value) {
    this.value = value;
  }
  public int val() {
    return value;
  }
}

public class Screen {

  private ArrayList<Furniture> problemSet;
  private ArrayList<Furniture> solutionSet;
  private ArrayList<Furniture> furnitureToDraw;
  private Room room;
  private DrawMode currentMode = DrawMode.PROBLEM;
  
  public void addRoom(Room room) {
    problemSet = new ArrayList<Furniture>();
    solutionSet = new ArrayList<Furniture>();
    furnitureToDraw = problemSet;
    this.room = room;
  }  
  
  public void addFurniture(Furniture f) {
    if (f.type() == DrawMode.PROBLEM) {
      problemSet.add(f);
    } else {
      solutionSet.add(f);
    }
  }
  
  public void switchDrawMode(DrawMode mode) {
    currentMode = mode;
    if (mode == DrawMode.PROBLEM) {
      furnitureToDraw = problemSet;
    } else {
      furnitureToDraw = solutionSet;
    }
  }
  
  public void hide() {
    room.hide();
    for (Furniture item : furnitureToDraw) {
      item.hide();
    }
  }
  
  public void show() {
    room.show();
    for (Furniture item : furnitureToDraw) {
      item.show();
    }
  }
  
  public void setScale(float s) {
    room.setScale(s);
    for (Furniture item : furnitureToDraw) {
      item.setScale(s);
    }
  }
  
  public void draw() {
    if (currentMode == DrawMode.PROBLEM) {
      for (Furniture item : furnitureToDraw) {
        item.draw();
      }
      room.draw();
    } else {
      room.draw();
      for (Furniture item : furnitureToDraw) {
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