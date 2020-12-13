package Day13;

import java.io.File;

import aocutil.Utilities;

public class D13P1 {
  public static void main(String[] args) {
    Schedule sched = new Schedule(Utilities.parseInputFile(new File(args[0])));

    System.out.printf("The value requested is %d%n", sched.part1());
  }
}
