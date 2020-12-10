package Day10;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import aocutil.Utilities;

public class D10P2 {
  public static void main(String[] args){
    ArrayList<Integer> adapters = Utilities.parseToInts(new File(args[0]));
    adapters.add(0);
    Collections.sort(adapters);
    adapters.add(adapters.get(adapters.size()-1) + 3);// add device joltage
    long arrangements = 1L;

    int[] tribb = {0, 1, 1, 2, 4, 7, 13};
    int prev = 0;
    int consec = 1;
    for(int i : adapters){
      if(i == prev+1){
        consec++;
      } else {
        arrangements *= tribb[consec];
        consec = 1;
      }
      prev = i;
    }

    System.out.printf("The total number of valid arrangements is %d\n", arrangements);
    alternateSolution(adapters);

  }

  public static void alternateSolution(ArrayList<Integer> list){
    HashMap<Integer, Long> solutions = new HashMap<Integer, Long>();
    
    solutions.put(list.get(0), 1L);
    for(int i = 1; i < list.size(); i++){
      long sum = 0;

      if(solutions.containsKey(list.get(i) - 1))
        sum += solutions.get(list.get(i) - 1);
      if(solutions.containsKey(list.get(i) - 2))  
        sum += solutions.get(list.get(i) - 2);
      if(solutions.containsKey(list.get(i) - 3))
        sum += solutions.get(list.get(i) - 3);
      
      solutions.put(list.get(i), sum);
    }
    System.out.printf("The total number of valid arrangements is %d\n", solutions.get(list.get(list.size()-1)));
  }
}
