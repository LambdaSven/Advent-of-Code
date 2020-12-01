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
                  //create new stream containing lists of 2 numbers (all possible 2 number combinations)
                  .flatMap(n1 -> data.stream().map(n2 -> Arrays.asList(n1, n2)))
                  //create stream containing lists of 3 numbers (all possible 3 number combinations)
                  .flatMap(l1 -> data.stream().map(n3 -> Arrays.asList(l1.get(0), l1.get(1), n3)))
                  //filter out all which don't produce the proper sum
                  .filter(e -> e.stream().reduce(0, Integer::sum) == TARGET)
                  //multiply elements of valid list (due to lazy evaluation this will only be done once)
                  .map(e -> e.stream().reduce(1, Math::multiplyExact))
                  //find valid product
                  .findFirst()
                  //retun -1 if no valid product exists
                  .orElse(-1);

    System.out.printf("Your Expense Report Error Code is %d\n", product);
  }
}
