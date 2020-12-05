package Day05;

import aocutil.Utilities;
import java.io.File;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class D5P2 {
  public static void main(String[] args){
    HashMap<Integer, BoardingPass> map = Utilities
    .parseInputFile(new File(args[0]))
    .stream()
    .map(BoardingPass::new)
    .collect(Collectors.toMap(BoardingPass::getChecksum, //Integer
                               Function.identity(),      //BoardingPass
                               (k1, K2) -> k1,           //Merging fn - resolves collisions
                               HashMap::new));
    
    //seats at front and back don't exist means our 
    //ID range is likely 1 * 8 + 0 = 8
    //                 126 * 8 + 7 = 1015
    for(int i = 8; i <= 1015; i++){
      if(!map.containsKey(i) && map.containsKey(i+1) && map.containsKey(i-1)){
        System.out.printf("Your seat ID is %d", i);
        return;
      }
    }
  }
}
