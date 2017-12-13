// Static helper functions

ArrayList<Float> costs = new ArrayList<Float>();

public PShape createPolygon(ArrayList<Float> coordinates) {
  PShape new_shape = createShape();
  new_shape.beginShape();
  new_shape.stroke(0);
  new_shape.strokeWeight(0.15);
  for (int i = 1; i < coordinates.size(); i += 2) {
    new_shape.vertex(coordinates.get(i - 1), coordinates.get(i));
  }     
  new_shape.endShape(CLOSE);
  return new_shape; 
}