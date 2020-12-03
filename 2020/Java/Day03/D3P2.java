package Day03;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D3P2 {
  public static void main(String[] args){
    ArrayList<String> input = Utilities.parseInputFile(new File(args[0]));
    Forest forest = new Forest(input);

    /*
      Since the result of part 1 was three digits, we have an estimated
      order of magnitude of 10¹⁰. This would be pushing the limits of an int
      and would be likely to overflow. A long has an order of magnitude of 
      10¹⁹, so the result will fit comfortably.
    */
    long treeCount = forest.treeCount(1, 1);
    treeCount *= forest.treeCount(3, 1);
    treeCount *= forest.treeCount(5, 1);
    treeCount *= forest.treeCount(7, 1);
    treeCount *= forest.treeCount(1, 2);

    System.out.printf("The result of Tree Multiplication is %d.", treeCount);
  }
}
