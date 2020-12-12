package Day12;

public class Waypoint {
  int x, y;

  public Waypoint(int x, int y){
    this.x = x;
    this.y = y;
  } 

  public void move(char dir, int magnitude){
    switch(dir){
      case 'N' -> y += magnitude;
      case 'S' -> y -= magnitude;
      case 'E' -> x += magnitude;
      case 'W' -> x -= magnitude;
    }
  }

  public void set(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void turn(char dir, int magnitude){
    System.out.printf(" TURN %d,%d → ", x, y);
    for(int i = 0; i < magnitude; i++){
      int temp = x;
      switch(dir){
        case 'R':
          temp = y;
          y = -x;
          x = temp;
          break;
        case 'L':
          temp = -y;
          y = x;
          x = temp;
          break;
      }
    }
    System.out.printf("%d,%d", x, y);
  }

  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
}
