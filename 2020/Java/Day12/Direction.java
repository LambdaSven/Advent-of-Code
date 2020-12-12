package Day12;

public enum Direction {
  NORTH{
    @Override
    public Direction prev(){
      return Direction.WEST;
    }
  }, 
  EAST, 
  SOUTH, 
  WEST{
    @Override
    public Direction next(){
      return Direction.NORTH;
    };
  };

  public Direction next(){
    return values()[ordinal()+1];
  }

  public Direction prev(){
    return values()[ordinal()-1];
  }

  public char toChar(){
    switch(this){
      case NORTH: return 'N';
      case EAST: return 'E';
      case SOUTH: return 'S';
      case WEST: return 'W';
    }
    return 'X';
  }
}
