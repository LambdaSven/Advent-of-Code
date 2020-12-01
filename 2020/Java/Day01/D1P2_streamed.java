package Day01;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import aocutil.Utilities;


/**
 * Example solution using Streams, this is not an ideal solution to this 
 * problem and only exists to showcase how streams can be used
 */
public class D1P2_streamed {
  public static void main(String[] args){
    final int TARGET = 2020;
    int product = 0;
    
    ArrayList<Integer> data = Utilities.parseToInts(new File(args[0]));

    //O(n³)
    product = data.stream()
                  .flatMap(n1 -> data.stream().map(n2 -> Arrays.asList(n1, n2)))
                  .flatMap(l1 -> data.stream().map(n3 -> Arrays.asList(l1.get(0), l1.get(1), n3)))
                  .filter(e -> e.stream().reduce(0, Integer::sum) == TARGET)
                  .map(e -> e.stream().reduce(1, Math::multiplyExact))
                  .findFirst()
                  .orElse(-1);

    System.out.printf("Your Expense Report Error Code is %d\n", product);
  }
}
