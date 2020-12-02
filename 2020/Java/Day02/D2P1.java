package Day02;

import java.io.File;

import aocutil.Utilities;
/**
 * Part 1 and Part 2 are almost exactly the same, the only 
 * distinction is the int paramater we are passing into our 
 * constructor
 */
public class D2P1 {
  public static void main(String[] args){
    long countValid = Utilities.parseInputFile(new File(args[0])) //ArrayList
      .stream()
      .map(e -> new Password(e, 1))
      .filter(Password::isValid)
      .count();

    System.out.printf("The number of Valid passwords in your list is %d\n", countValid);
  }
}
