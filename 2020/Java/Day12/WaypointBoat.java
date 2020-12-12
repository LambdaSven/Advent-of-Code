package Day12;

import java.util.ArrayList;

public class WaypointBoat {
  int x, y;
  Waypoint waypoint = new Waypoint(10, 1);
  ArrayList<String> instructions;

  public WaypointBoat(ArrayList<String> in){
    instructions = in;
    x = 0;
    y = 0;
  }

  public void run(){
    for(String s : instructions){
      char instruction = s.charAt(0);
      int magnitude = Integer.parseInt(s.substring(1));
      switch(instruction){
        case 'N', 'S', 'E', 'W' -> waypoint.move(instruction, magnitude);
        case 'F' -> move(magnitude);
        case 'L', 'R' -> waypoint.turn(instruction, magnitude/90);
      }
      System.out.printf("\n%d,%d", x, y);
    }
  }

  private void move(int magnitude){
    for(int i = 0; i < magnitude; i++){
      this.x += waypoint.getX();
      this.y += waypoint.getY();
    }
  }

  public int manhattan(){
    return Math.abs(x) + Math.abs(y);
  }
}
