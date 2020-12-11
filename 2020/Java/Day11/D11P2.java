package Day11;

import java.io.File;

import aocutil.Utilities;

public class D11P2 {
  public static void main(String[] args){
    Lobby lobby = new Lobby(Utilities.parseInputFile(new File(args[0])));

    while(!lobby.isStable()){
      lobby.simulateFixed(1);
    }

    System.out.printf("The number of seats occupied in a stable state is %d\n", lobby.numberOccupied());
  }
}
