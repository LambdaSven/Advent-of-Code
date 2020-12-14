package Day14;

import java.io.File;

import aocutil.Utilities;

public class D14P2 {
  public static void main(String[] args) {
    DockingComputerV2 dc = new DockingComputerV2(Utilities.parseInputFile(new File(args[0])));
    dc.run();

    System.out.printf("The value requested is %d%n", dc.memsum());
  }
}
