package Day13;

import java.io.File;

import aocutil.Utilities;

public class D13P2 {
  public static void main(String[] args) {
    Contest con = new Contest(Utilities.parseInputFile(new File(args[0])));

    System.out.printf("The value requested is %d%n", con.part2());
  }
}
