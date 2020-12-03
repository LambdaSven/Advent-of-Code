package Day03;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D3P2 {
  public static void main(String[] args){
    ArrayList<String> input = Utilities.parseInputFile(new File(args[0]));
    Forest forest = new Forest(input);

    long treeCount = forest.treeCount(1, 1);
    treeCount *= forest.treeCount(3, 1);
    treeCount *= forest.treeCount(5, 1);
    treeCount *= forest.treeCount(7, 1);
    treeCount *= forest.treeCount(1, 2);

    System.out.printf("The result of Tree Multiplication is %d.", treeCount);
  }
}
