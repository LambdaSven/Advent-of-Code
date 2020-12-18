package Day17;

import java.util.Objects;

public class FourPoint {
  public int x, y, z, w;

  public FourPoint(int x, int y, int z, int w){
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  @Override
  public boolean equals(Object o){
    if(o == this)
      return true;

    if(!(o instanceof FourPoint))
      return false;
    
    FourPoint p = (FourPoint) o;

    return p.x == this.x && p.y == this.y && p.z == this.z && p.w == this.w;
  }

  @Override
  public int hashCode(){
    return Objects.hash(x, y, z, w);
  }
}
