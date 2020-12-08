package Day08;

import java.io.File;

import aocutil.Utilities;

public class D8P1 {
  public static void main(String[] args){
    Bootcode computer = new Bootcode(Utilities.parseInputFile(new File(args[0])));
    computer.run();

    System.out.printf("The value of the accumulator at halting time is %d\n", computer.getAccumulator());
  }
}
