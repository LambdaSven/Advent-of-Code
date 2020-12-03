package Day03;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D3P1 {
  public static void main(String[] args){
    ArrayList<String> input = Utilities.parseInputFile(new File(args[0]));
    Forest forest = new Forest(input);

    int treeCount = forest.treeCount(3, 1);
    System.out.printf("You will encounter %d trees on your route.\n", treeCount);
  }
}
