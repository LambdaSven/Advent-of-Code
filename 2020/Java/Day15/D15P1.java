package Day15;

import java.io.File;

import aocutil.Utilities;

public class D15P1 {
  public static void main(String[] args) {
    Game game = new Game(Utilities.parseInputFile(new File(args[0])));

    game.run(2020);

    System.out.printf("The 2020th number spoken is: %d%n", game.getNum());
  }
}
