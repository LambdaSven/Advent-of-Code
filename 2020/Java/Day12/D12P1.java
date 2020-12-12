package Day12;

import java.io.File;

import aocutil.Utilities;

public class D12P1 {
  public static void main(String[] args) {
    Boat boat = new Boat(Utilities.parseInputFile(new File(args[0])));

    boat.run();
    System.out.printf("The manhattan distance from start is %d\n", boat.manhattan());
  }
}
