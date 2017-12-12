public class ScaleSlider {
  
  private final float MIN_SCALE_AMOUNT = 10;
  private final float MAX_SCALE_AMOUNT = 500;
  
  private final float bar_x, bar_y, width, height;
  private final float slider_min_pos, slider_max_pos;
  private float slider_x, slider_y, slider_width, slider_height;
  private boolean mouse_pressed_slider = false;
  
  public ScaleSlider(float x, float y, float width, float height) {
    bar_x = x;
    bar_y = y;
    this.width = width;
    this.height = height;
    slider_x = bar_x;
    slider_y = bar_y - (height / 2);
    slider_width = width * 1.4;
    slider_height = height / 15;
    slider_min_pos = bar_y - (height / 2);
    slider_max_pos = bar_y + (height / 2);
  }
  
  public void draw() {
    checkMouse();
    if (mouse_pressed_slider) {
      slider_y = constrain(mouseY, slider_min_pos, slider_max_pos);
    }
    stroke(#429bf4);
    fill(0);
    rectMode(CENTER);
    rect(bar_x, bar_y, width, height);
    fill(#429bf4);
    rect(slider_x, slider_y, slider_width, slider_height);
    screenManager.setRoomScale((slider_max_pos - slider_y) * ((MAX_SCALE_AMOUNT - MIN_SCALE_AMOUNT) / height));
  }
  
  private void checkMouse() {
    if (mousePressed) {
      if (mouseX >= slider_x - (slider_width / 2) && mouseX <= slider_x + (slider_width / 2) &&
          mouseY >= slider_y - (slider_height / 2) && mouseY <= slider_y + (slider_height / 2)) {
          mouse_pressed_slider = true;
      }
    } else {
      mouse_pressed_slider = false;
    }
  }
  
}