package Day15;

import java.io.File;

import aocutil.Utilities;

public class D15P2 {
  public static void main(String[] args) {
    Game game = new Game(Utilities.parseInputFile(new File(args[0])));

    game.run(30000000);

    System.out.printf("The 30000000th number spoken is: %d%n", game.getNum());
  }
}
