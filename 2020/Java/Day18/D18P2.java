package Day18;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.Collectors;

import aocutil.Utilities;

public class D18P2 {
  public static void main(String[] args) {
    ArrayList<Calculator> calcs = Utilities.parseInputFile(new File(args[0]))
                                           .stream()
                                           .map(e -> new Calculator(e))
                                           .collect(Collectors.toCollection(ArrayList::new));

    BigInteger result = calcs.stream()
                             .peek(e -> e.setAdvanced())
                             .map(Calculator::calcRPN)
                             .map(e -> BigInteger.valueOf(e))
                             .reduce(BigInteger.ZERO, BigInteger::add);

    System.out.printf("Repeated summation of results = %d%n", result);
  }
}
