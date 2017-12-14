public class Furniture implements Drawable {
  
  private static final float MAX_SPEED = 3f;
  private static final float COST_RANGE = 169f; // highest possible cost in sample set is 5408
  private static final color MIN_COLOUR = #B7ECFF; // (light blue)
  private static final color MAX_COLOUR = #FA0D00; // (strong red)
  
  private final color m_colour;
  private final Screen screen;
  private PShape m_shape;
  private float pos_x, pos_y;
  private float velocity_x, velocity_y;
  private float m_scale = 1.0;
  private DrawMode drawMode;
  
  public Furniture(ArrayList<Float> vertices, int cost, Screen screen, DrawMode drawMode) {
    m_shape = createPolygon(vertices);
    m_colour = lerpColor(MIN_COLOUR, MAX_COLOUR, cost / COST_RANGE);
    m_shape.setFill(m_colour);
    this.screen = screen;
    this.drawMode = drawMode;
    if (drawMode == DrawMode.PROBLEM) {
      randomise();
    } 
  }
  
  public void show() {
    m_shape.setVisible(true);
    if (drawMode == DrawMode.PROBLEM) {
      randomise();
    }
  }
  
  private void randomise() {
    pos_x = random(WINDOW_WIDTH);
    pos_y = random(WINDOW_HEIGHT);
    velocity_x = random(-MAX_SPEED, MAX_SPEED);
    velocity_y = random(-MAX_SPEED, MAX_SPEED);
  }
  
  public DrawMode type() {
    return drawMode;
  }
  
  private void updatePos() {
    pos_x += velocity_x;
    pos_y += velocity_y;
  }
  
  private void clampToScreen() {
    if (pos_x > WINDOW_WIDTH + (m_shape.width * m_scale)) {
      pos_x = -m_shape.width * m_scale;
    } else if (pos_x < (-m_shape.width * m_scale)) {
      pos_x = WINDOW_WIDTH + (m_shape.width * m_scale);
    }
    
    if (pos_y > WINDOW_HEIGHT + (m_shape.height * m_scale)) {
      pos_y = -m_shape.height * m_scale;
    } else if (pos_y < -m_shape.height * m_scale) {
      pos_y = WINDOW_HEIGHT + (m_shape.height * m_scale);
    }
  }
  
  public void setScale(float s) {
    m_scale = s;
  }
  
  public void hide() {
    m_shape.setVisible(false);
  }
  
  public void move(int x, int y) {
    m_shape.translate(x, y);
  }
  
  public int getWidth() {
    return round(m_shape.width);
  }
  
  public int getHeight() {
    return round(m_shape.height);
  }
  
  public void draw() {
    updatePos();
    stroke(0);
    fill(m_colour);
    if (drawMode == DrawMode.SOLUTION) {
      // translate object so that origin is at room's top-left corner
      pushMatrix();
      translate(screen.getRoomX(), screen.getRoomY());
      scale(m_scale);
      shape(m_shape, 0, 0);
      popMatrix();
    } else {
      pushMatrix();
      scale(m_scale);
      shapeMode(CENTER);
      shape(m_shape, pos_x / m_scale, pos_y / m_scale);
      popMatrix();
      clampToScreen();
    }
    
  }
}