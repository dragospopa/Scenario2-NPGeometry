public class ScreenManager {
  
  private ArrayList<Screen> screens = new ArrayList<Screen>();
  private int active_screen = 0;
  
  public ScreenManager() {}
  
  public Screen getScreen(int i) {
    return screens.get(i - 1);
  }
  
  public void clearActiveScreen() {
    screens.get(active_screen).clearFurniture();
  }
  
  public void addScreen(Screen screen) {
    screens.add(screen);
  }
  
  public void setRoomScale(float s) {
    for (Screen screen : screens) {
      screen.setScale(s);
    }
  }
  
  public void switchToScreen(int i) {
    for (Screen screen : screens) {
      screen.hide();
    }
    screens.get(i - 1).show();
    active_screen = i - 1;
  }
  
  public void drawActiveScreen() {
    //println("Drawing screen " + active_screen + "...");
    screens.get(active_screen).draw();
  }
  
}