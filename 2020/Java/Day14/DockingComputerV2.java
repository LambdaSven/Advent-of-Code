package Day14;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class DockingComputerV2 {
  HashMap<Long, Long> memory = new HashMap<Long, Long>();
  ArrayList<String> instructions;
  long bitmask, floatMask;
  ArrayList<Long> floats;

  public DockingComputerV2(ArrayList<String> in){
    instructions = in;
  }

  public void run(){
    for(String s : instructions){
      if(s.contains("mask =")){
        parseMask(s.replace("mask = ", ""));
      } else {
        long address = (Integer.parseInt(s.substring(s.indexOf("[")+1, s.indexOf(']'))) | bitmask) & ~floatMask;
        long value = Integer.parseInt(s.substring(s.indexOf("=")+2));
        
        for(long l : floats){
          long write = (address | l);
          memory.put(write, value);
        }
      }
    }
  }

  private void parseMask(String s){
    bitmask = Long.parseLong(s.replaceAll("X", "0"), 2);

    floats = new ArrayList<Long>();
    floats.add(0L);
    floatMask = Long.parseLong(s.replaceAll("[^X]", "0").replaceAll("X", "1"), 2);

    for(long i = 0, j = 1; j < floatMask; i++, j *= 2){
      if((floatMask >> i & 1) != 0){
        floats.add(j);
      }
    }

    for(int i = 0; i < floats.size(); i++){
      for(int j = 0; j < floats.size(); j++){
        if(!floats.contains(floats.get(i) | floats.get(j))){
           floats.add(floats.get(i) | floats.get(j));
          }
      }
    }

  }

  public BigInteger memsum(){
    BigInteger out = BigInteger.ZERO;

    for(long i : memory.keySet()){
      out = out.add(BigInteger.valueOf(memory.get(i)));
    }

    return out;
  }
}
