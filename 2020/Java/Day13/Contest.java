package Day13;

import java.math.BigInteger;
import java.util.ArrayList;

public class Contest {
  ArrayList<Integer> busses = new ArrayList<Integer>();

  public Contest(ArrayList<String> in){

    String[] temp = in.get(1).split(",");
    for(String s : temp){
      if(!s.equals("x")){
        busses.add(Integer.parseInt(s));
      } else {
        busses.add(1);
      }
    }
  }

  public BigInteger part2(){
    long modProd = busses.stream()
                         .map(e -> (long) e)
                         .reduce(1L, Math::multiplyExact);


    ArrayList<Long> rems = new ArrayList<Long>();
    ArrayList<Long> mods = new ArrayList<Long>();
    rems.add(0L);
    mods.add((long) busses.get(0));
    for(int i = 1; i < busses.size(); i++){
      if(busses.get(i) != 1){
        rems.add((long) busses.get(i) - i);
        mods.add((long) busses.get(i));
      }
    }

    ArrayList<Long> bases = new ArrayList<Long>();
    for(Long i : mods){
      bases.add(modProd/i);
    }

    ArrayList<Long> xinv = new ArrayList<Long>();
    for(int i = 0; i < bases.size(); i++){
      long temp = bases.get(i) % mods.get(i);
      for(long j = 0;;j++){
        if(temp * j % mods.get(i) == 1){
          xinv.add(j);
          break;
        }
      }
    }

    BigInteger sum = BigInteger.ZERO;
    for(int i = 0; i < mods.size(); i++){
      sum = sum.add(BigInteger.valueOf(rems.get(i) * bases.get(i) * xinv.get(i)));
    }
    return sum.mod(BigInteger.valueOf(modProd));
  }
}
