package Day09;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D9P2 {
  final static Long TARGET = 21806024L;

  public static void main(String[] args){
    ArrayList<Long> xmas = Utilities.parseToLong(new File(args[0]));
    Long largest = -1L, smallest = -1L;
    boolean found = false;

    for(int i = 0; i < xmas.size() && !found; i++){
      Long sum = xmas.get(i);
      smallest = xmas.get(i);
      largest = smallest;
      
      for(int j = i+1; j < xmas.size(); j++){
        sum += xmas.get(j);
        if(xmas.get(j) > largest)
          largest = xmas.get(j);
        if(xmas.get(j) < smallest)
          smallest = xmas.get(j);

        if(sum > TARGET){
          break;
        } else if (sum.equals(TARGET)){
          //found!!
          found = true;
          break;
        } 
      }
    }

    Long sum = largest + smallest;
    System.out.printf("The encryption weakness is %d\n", sum);
  }
}
