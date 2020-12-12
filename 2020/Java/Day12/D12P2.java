package Day12;

import java.io.File;

import aocutil.Utilities;

public class D12P2 {
  public static void main(String[] args) {
    WaypointBoat boat = new WaypointBoat(Utilities.parseInputFile(new File(args[0])));

    boat.run();
    System.out.printf("The manhattan distance from start is %d\n", boat.manhattan());
  }
}
