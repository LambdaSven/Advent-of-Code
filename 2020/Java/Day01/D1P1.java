package Day01;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import aocutil.Utilities;

/**
 * Solution for Day 1, Part 1 in O(n) using caching
 */
public class D1P1 {
  public static void main(String[] args){
    final int TARGET = 2020;
    int product = 0;
    
    ArrayList<Integer> data = Utilities.parseToInts(new File(args[0]));
    Set<Integer> cache = new HashSet<Integer>();
    cache.add(data.get(0));

    //O(n)
    for(int i = 1; i < data.size(); i++){
      // if the set contains some a = x - b
      // then we know that a + b = x
      if(cache.contains(TARGET - data.get(i))){
        //match!
        product = data.get(i) * (TARGET - data.get(i));
        break;
      }
      cache.add(data.get(i));
    }

    System.out.printf("Your Expense Report Error Code is %d\n", product);
  }
}
