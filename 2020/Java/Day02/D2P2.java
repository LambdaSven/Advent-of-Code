package Day02;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import aocutil.Utilities;

/**
 * Part 1 and Part 2 are almost exactly the same, the only 
 * distinction is the int paramater we are passing into our 
 * constructor
 */
public class D2P2 {
  public static void main(String[] args){
    ArrayList<String> input = Utilities.parseInputFile(new File(args[0]));
    ArrayList<Password> passwords = input.stream()
                                          .map(e -> new Password(e, 2))
                                          .collect(Collectors.toCollection(ArrayList::new));

    long countValid = passwords.stream()
                              .filter(Password::isValid)
                              .count();

    System.out.printf("The number of Valid passwords in your list is %d\n", countValid);
  }
}
