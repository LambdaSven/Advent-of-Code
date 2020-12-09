package Day09;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import aocutil.Utilities;

public class D9P1 {
  public static void main(String[] args){
    ArrayList<Long> xmas = Utilities.parseToLong(new File(args[0]));

    int lowerEdge = 0;
    int upperEdge = 25; // arraylist is exclusive on the upper bounds
    Long result = 0L;

    for(int i = 25; i < xmas.size(); i++){
      long target = xmas.get(i);
      if(!isValid(target, xmas.subList(lowerEdge, upperEdge))){
        result = xmas.get(i);
        break;
      } else {
        lowerEdge++;
        upperEdge++;
      }
    }

    System.out.printf("The result is %d\n", result);

  }

  public static boolean isValid(Long n, List<Long> preamble){
    for(Long i : preamble){
      if(preamble.contains(n - i)){
        return true;
      }
    }
    return false;
  }
}
