package Day12;

import java.util.ArrayList;

public class Boat {
  int x, y;
  Direction direction;
  ArrayList<String> instructions;

  public Boat(ArrayList<String> in){
    instructions = in;
    x = 0;
    y = 0;
    direction = Direction.EAST;
  }

  public void run(){
    for(String s : instructions){
      char instruction = s.charAt(0);
      int magnitude = Integer.parseInt(s.substring(1));
      switch(instruction){
        case 'N', 'S', 'E', 'W' -> move(instruction, magnitude);
        case 'F' -> move(direction.toChar(), magnitude);
        case 'L', 'R' -> turn(instruction, magnitude/90);
      }
    }
  }

  private void move(char dir, int magnitude){
    switch(dir){
      case 'N' -> y += magnitude;
      case 'E' -> x += magnitude;
      case 'S' -> y -= magnitude;
      case 'W' -> x -= magnitude;
    }
  }

  private void turn(char dir, int magnitude){
    switch(dir){
      case 'L':
        for(int i = 0; i < magnitude; i++){
          direction = direction.prev();
        }
        return;
      case 'R':
        for(int i = 0; i < magnitude; i++){
          direction = direction.next();
        }
        return;
    }
  }

  public int manhattan(){
    return Math.abs(x) + Math.abs(y);
  }
}
