public class Button implements Drawable {
  private final float width = WINDOW_WIDTH / 12;
  private final float height = WINDOW_HEIGHT / 12;
  private final color NORMAL_COLOUR = 255;
  private final color HIGHLIGHT_COLOUR = #7fa5e2;
  private final color PRESSED_COLOUR = #59cc8f;
  
  private PShape rect;
  private color current_colour = 255;
  private int btn_number;
  private int pos_x, pos_y;
  private boolean mouse_touching;
  
  public Button(int number) {
    btn_number = number;
    rectMode(CENTER);
    rect = createShape(RECT, 0, 0, width, height); 
    rect.setFill(255);
    rect.setStroke(0);
    rect.setStrokeWeight(4);
  }
  
  public void draw() {
    
    checkForMouseTouch();
    
    if (mouse_touching) {
      current_colour = HIGHLIGHT_COLOUR;
    } else {
      current_colour = NORMAL_COLOUR;
    }
    rect.setFill(current_colour);
    shape(rect);
    fill(0);
    textSize(70);
    text(String.valueOf(btn_number), pos_x, pos_y + 20);
  }
  
  private void checkForMouseTouch() {
    if (mouseX >= pos_x - (width / 2) && mouseX <= pos_x + (width / 2) &&
        mouseY >= pos_y - (height / 2) && mouseY <= pos_y + (height / 2)) {
      mouse_touching = true; 
    } else {
      mouse_touching = false;
    } 
  }
  
  void mousePressed() {
    if (mouse_touching) {
      current_colour = PRESSED_COLOUR;
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
}