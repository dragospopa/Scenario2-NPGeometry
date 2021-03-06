import java.util.List;

public static final int WINDOW_WIDTH = 1600;
public static final int WINDOW_HEIGHT = 1600;

// no. of pixels per unit of drawing space
public static final int PPU = 90;

void settings() {
  size(WINDOW_WIDTH, WINDOW_HEIGHT);
}

ScreenManager screenManager;
MenuBar buttonMenu;
ScaleSlider roomSlider;
String screenPrompt = "Press RETURN to see solution";
color promptFill = #31D800;

void setup() {
  smooth();
  noStroke();
  screenManager = new ScreenManager();
  buttonMenu = new MenuBar(WINDOW_WIDTH / 14, round(WINDOW_HEIGHT * 0.83));
  roomSlider = new ScaleSlider(WINDOW_WIDTH * 0.9, WINDOW_HEIGHT / 2, WINDOW_WIDTH / 30, WINDOW_HEIGHT / 2);
  parseProblems();
  parseSolutions(); //<>// //<>//
}

void draw() {
  background(#77939b);
  textSize(65);
  textAlign(CENTER);
  strokeWeight(3);
  screenManager.drawActiveScreen();
  screenManager.setScreenPrompt();
  fill(255);
  text("Furnishing Crab Caves - a visualisation", WINDOW_WIDTH / 2, WINDOW_HEIGHT / 20);
  strokeWeight(0);
  rect(WINDOW_WIDTH / 2, WINDOW_HEIGHT * 0.98, WINDOW_WIDTH, WINDOW_HEIGHT / 13);
  fill(promptFill);
  text(screenPrompt, WINDOW_WIDTH / 2, WINDOW_HEIGHT * 0.98);
  stroke(0);
  roomSlider.draw();
  buttonMenu.draw();
}

void keyPressed() {
  switch (keyCode) {
    case ENTER:
      screenManager.switchActiveScreenDrawMode(DrawMode.SOLUTION);
      break;
    case BACKSPACE:
      screenManager.switchActiveScreenDrawMode(DrawMode.PROBLEM);
      break;
  }
}