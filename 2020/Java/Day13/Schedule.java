package Day13;

import java.math.BigInteger;
import java.util.ArrayList;

public class Schedule {
  BigInteger earliest;
  ArrayList<Integer> busses = new ArrayList<Integer>();

  public Schedule(ArrayList<String> in){
    earliest = new BigInteger(in.get(0));

    String[] temp = in.get(1).split(",");
    for(String s : temp){
      if(!s.equals("x")){
        busses.add(Integer.parseInt(s));
      }
    }
  }

  public int part1(){
    Integer distance = Integer.MAX_VALUE;
    Integer bus = 0;

    for(Integer i : busses){
      int temp = earliest.mod(BigInteger.valueOf(i)).intValue();
      temp = i-temp;
      if(temp < distance){
        distance = temp;
        bus = i;
      }
    }
    return bus * distance;
  }
}
