package Day11;

import java.util.ArrayList;

public class Lobby {
  Seat[][] map;
  boolean stable = false;

  public Lobby(ArrayList<String> in){
    map = new Seat[in.size()][]; // initalize number of rows

    for(int i = 0; i < in.size(); i++){
      map[i] = new Seat[in.get(i).length()];// initalize number of columns
      for(int j = 0; j < map[i].length; j++){
        switch(in.get(i).charAt(j)){
          // Switch Expression requires Java 14+
          case '.' -> map[i][j] = Seat.FLOOR;
          case 'L' -> map[i][j] = Seat.EMPTY;
          case '#' -> map[i][j] = Seat.OCCUPIED;
        }
      }
    }
  }

  public void simulate(int Rounds){
    for(int n = 0; n < Rounds && !stable; n++){
      stable = true;
      Seat[][] tempMap = new Seat[map.length][];
      for(int i = 0; i < map.length; i++){
        tempMap[i] = new Seat[map[i].length];
        for(int j = 0; j < map[i].length; j++){
          int numAdj = getAdj(j, i);
          if(map[i][j].equals(Seat.EMPTY) && numAdj == 0){
            tempMap[i][j] = Seat.OCCUPIED;
            stable = false;
          } else if(map[i][j].equals(Seat.OCCUPIED) && numAdj > 3){
            tempMap[i][j] = Seat.EMPTY;
            stable = false;
          } else {
            tempMap[i][j] = map[i][j];
          }
        }
      }
      mapCopy(tempMap);
    }
  }
  
  public void simulateFixed(int Rounds){
    for(int n = 0; n < Rounds && !stable; n++){
      stable = true;
      Seat[][] tempMap = new Seat[map.length][];
      for(int i = 0; i < map.length; i++){
        tempMap[i] = new Seat[map[i].length];
        for(int j = 0; j < map[i].length; j++){
          int numAdj = getAdjFixed(j, i);
          if(map[i][j].equals(Seat.EMPTY) && numAdj == 0){
            tempMap[i][j] = Seat.OCCUPIED;
            stable = false;
          } else if(map[i][j].equals(Seat.OCCUPIED) && numAdj > 4){
            tempMap[i][j] = Seat.EMPTY;
            stable = false;
          } else {
            tempMap[i][j] = map[i][j];
          }
        }
      }
      mapCopy(tempMap);
    }
  }

  private void mapCopy(Seat[][] src){
    for(int i = 0; i < src.length; i++){
      for(int j = 0; j < src[i].length; j++){
        map[i][j] = src[i][j];
      }
    }
  }

  private int getAdj(int x, int y){
    int adj = 0;
    for(int i = -1; i < 2; i++){
      for(int j = -1; j < 2; j++){
        int targetX = x + j;
        int targetY = y + i;
        
        if(j == 0 && i == 0){
          continue; // don't count my own seat

        } else if(targetY >= 0 && targetY < map.length && 
                  targetX >= 0 && targetX < map[targetY].length){
          if(map[targetY][targetX].equals(Seat.OCCUPIED)){
            adj++;
          }
        }
      }
    }
    return adj;
  }

  private int getAdjFixed(int x, int y){
    int adj = 0;
    for(int i = -1; i < 2; i++){
      for(int j = -1; j < 2; j++){
        if(i == 0 && j == 0)
          continue;
        int targetX = x + j;
        int targetY = y + i;
        while(targetY >= 0 && targetY < map.length && 
              targetX >= 0 && targetX < map[targetY].length){

            if(map[targetY][targetX].equals(Seat.FLOOR)){
              targetX += j;
              targetY += i;
            } else if(map[targetY][targetX].equals(Seat.OCCUPIED)){
              adj++;
              break;
            } else {
              break;
            }
          }
        }
      }
    return adj;
  }

  public boolean isStable(){
    return stable;
  }

  public int numberOccupied(){
    int count = 0;
    for(int i = 0; i < map.length; i++){
      for(int j = 0; j < map[i].length; j++){
        if(map[i][j].equals(Seat.OCCUPIED))
          count++;
      }
    }
    return count;
  }

  public void print(){
    for(int i = 0; i < map.length; i++){        
      String line = "";
      for(int j = 0; j < map[i].length; j++){
        switch(map[i][j]){
          case EMPTY -> line = line.concat("L");
          case OCCUPIED -> line = line.concat("#");
          case FLOOR -> line = line.concat(".");
        }
      }
      line = line.concat("\t");
      for(int j = 0; j < map[i].length; j++){
        line = line.concat(Integer.toString(getAdj(j, i)));
      }
      System.out.println(line);
    }
  }
}

