public class ScreenManager {
  
  private ArrayList<Screen> screens = new ArrayList<Screen>();
  private int active_screen = 0;
  
  public ScreenManager() {}
  
  public Screen getScreen(int i) {
    return screens.get(i - 1);
  }
  
  public void switchActiveScreenDrawMode(DrawMode mode) {
    screens.get(active_screen).switchDrawMode(mode);
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
  
  public void setScreenPrompt() {
    if (screens.get(active_screen).getDrawMode() == DrawMode.PROBLEM) {
      screenPrompt = "Press RETURN to see solution";
      promptFill = #31D800;
    } else {
      screenPrompt = "Press BACKSPACE to see problem space";
      promptFill = #0295ED;
    }
  }
  
  public void drawActiveScreen() {
    //println("Drawing screen " + active_screen + "...");
    screens.get(active_screen).draw();
  }
  
}