package Day17;

import java.util.Objects;

public class Point {
  public int x, y, z;

  public Point(int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public boolean equals(Object o){
    if(o == this)
      return true;

    if(!(o instanceof Point))
      return false;
    
    Point p = (Point) o;

    return p.x == this.x && p.y == this.y && p.z == this.z;
  }

  @Override
  public int hashCode(){
    return Objects.hash(x, y, z);
  }
}
