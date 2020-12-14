package Day14;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class DockingComputer {
  HashMap<Integer, Long> memory = new HashMap<Integer, Long>();
  ArrayList<String> instructions;
  long bitmaskAnd, bitmaskOr;

  public DockingComputer(ArrayList<String> in){
    instructions = in;
  }

  public void run(){
    for(String s : instructions){
      if(s.contains("mask =")){
        parseMask(s.replace("mask = ", ""));
      } else {
        int address = Integer.parseInt(s.substring(s.indexOf("[")+1, s.indexOf(']')));
        long value = Integer.parseInt(s.substring(s.indexOf("=")+2));
        store(address, value);
      }
    }
  }

  private void store(int address, long value){
    memory.put(address, (value | bitmaskOr) & bitmaskAnd);
  }

  private void parseMask(String s){
    String temp = s.replaceAll("X", "1"); //for and
    bitmaskAnd = Long.parseLong(temp, 2);
    temp = s.replaceAll("X", "0"); //for or
    bitmaskOr = Long.parseLong(temp, 2);
  }

  public BigInteger memsum(){
    BigInteger out = BigInteger.ZERO;

    for(int i : memory.keySet()){
      out = out.add(BigInteger.valueOf(memory.get(i)));
    }

    return out;
  }
}
