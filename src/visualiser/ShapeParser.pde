// Static helper functions

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

public void parseProblems() {
  // parse problem set
  int num_rooms = 0;
  String[] lines = loadStrings("input.txt");
  for (String line : lines) {
    Screen screen = new Screen();
    
    String filtered = line.replaceAll("[(),]", "");
    String[] temp = filtered.split("# ");
    String roomCoords = temp[0];
    
    int start_index = roomCoords.indexOf(":") + 2;
    roomCoords = roomCoords.substring(start_index);
    
    ArrayList<Float> roomVertices = new ArrayList<Float>();
    for (String arg : roomCoords.split(" ")) {
      roomVertices.add(Float.parseFloat(arg));
    }
    screen.addRoom(new Room(roomVertices));
    
    String itemCoords = temp[1];
    
    for (String arg: itemCoords.split(";")) {
      int colon_index = arg.indexOf(":");
      int cost = Integer.parseInt(arg.substring(0, colon_index));
      String contents = arg.substring(colon_index + 1);
      ArrayList<Float> objectVertices = new ArrayList<Float>();
      for (String arg2: contents.split(" ")) {
        objectVertices.add(Float.parseFloat(arg2));
      }
      screen.addFurniture(new Furniture(objectVertices, cost, screen, false));
    }
    screenManager.addScreen(screen);
    buttonMenu.addMenuItem(new Button(++num_rooms));
  }
  screenManager.switchToScreen(1);
}

public void parseSolutions() {
  screenManager.clearActiveScreen();
  // parse solution set
  String[] lines = loadStrings("output.txt");
  for (String line : lines) {
    int colon_index = line.indexOf(":");
    int room_number = Integer.parseInt(line.substring(0, colon_index));
    String contents = line.substring(colon_index + 1);
    Screen screen = screenManager.getScreen(room_number);
    String filtered = contents.replaceAll("[() ]", "");
    for (String arg : filtered.split(";")) {
      ArrayList<Float> furnitureVerts = new ArrayList<Float>();
      for (String arg2 : arg.split(",")) {
        furnitureVerts.add(Float.parseFloat(arg2));
      }
      screen.addFurniture(new Furniture(furnitureVerts, 0, screen, true));
    }
  }
}