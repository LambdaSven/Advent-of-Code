package Day17;

import java.io.File;

import aocutil.Utilities;

public class D17P1 {
  public static void main(String[] args) {
    Conway conway = new Conway(Utilities.parseInputFile(new File(args[0])));

    conway.run(6);

    System.out.printf("The live cell count is %d%n", conway.getLive());
  }
}
