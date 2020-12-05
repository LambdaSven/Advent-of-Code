package Day05;

import aocutil.Utilities;
import java.io.File;
import java.util.ArrayList;

public class D5P1 {
  public static void main(String[] args){
    int result = Utilities.parseInputFile(new File(args[0]))
                          .stream()
                          .map(BoardingPass::new)
                          .mapToInt(BoardingPass::getChecksum)
                          .max()
                          .orElse(-1);

    System.out.printf("The maximum boarding pass valuse is %d", result);
  }
}
