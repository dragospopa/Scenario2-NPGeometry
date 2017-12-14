public class Button implements Drawable {
  private final float width = WINDOW_WIDTH / 18;
  private final float height = WINDOW_HEIGHT / 18;
  private final color NORMAL_COLOUR = 255;
  private final color HIGHLIGHT_COLOUR = #7fa5e2;
  private final color ACTIVE_COLOUR = #FFB003;
  private final color PRESSED_COLOUR = #59cc8f;
  
  private PShape rect;
  private color current_fill = 255;
  private color current_stroke = 0;
  private int btn_number;
  private int pos_x, pos_y;
  private boolean mouse_touching;
  private boolean is_active = false;
  
  public Button(int number) {
    btn_number = number;
    rectMode(CENTER);
    rect = createShape(RECT, 0, 0, width, height); 
    rect.setFill(current_fill);
    rect.setStroke(current_stroke);
    rect.setStrokeWeight(4);
  }
  
  public void draw() {
    checkForMouseTouch();
    if (mouse_touching) {
      current_fill = HIGHLIGHT_COLOUR;
      if (mousePressed) {
        //setActive();
        screenManager.switchToScreen(btn_number);
      }
    } else {
      current_fill = NORMAL_COLOUR;
    }
    rect.setStroke(current_stroke);
    rect.setFill(current_fill);
    shape(rect);
    fill(0);
    textSize(55);
    text(String.valueOf(btn_number), pos_x, pos_y + 20);
  }
  
  //public void setActive() {
  //  is_active = true;
  //  current_fill = ACTIVE_COLOUR;
  //}
  
  private void checkForMouseTouch() {
    if (mouseX >= pos_x - (width / 2) && mouseX <= pos_x + (width / 2) &&
        mouseY >= pos_y - (height / 2) && mouseY <= pos_y + (height / 2)) {
      mouse_touching = true; 
    } else {
      mouse_touching = false;
    } 
  }
  
  public void show() {
    rect.setVisible(true);
  }
  
  public void hide() {
    rect.setVisible(false);
  }
  
  public void move(int x, int y) {
    rect.translate(x, y);
    pos_x += x;
    pos_y += y;
  }
  
  public int getWidth() {
    return round(width);
  }
  
  public int getHeight() {
    return round(height);
  }
}