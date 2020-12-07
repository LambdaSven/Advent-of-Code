package Day07;

import java.io.File;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import aocutil.Utilities;

public class D7P1 {
  public static void main(String[] args){

    HashMap<String, Bag> bags = Utilities
                        .parseInputFile(new File(args[0]))
                        .stream()
                        .map(Bag::new)
                        .collect(Collectors.toMap(Bag::getId,
                                                  Function.identity(),
                                                  (k1, k2) -> k1,
                                                  HashMap::new));
    
    System.out.printf("There are %d valid bags", part1Count(bags));
  }

  public static int part1Count(HashMap<String, Bag> bags){
    int count = 0;
    for(String s : bags.keySet()){

      if(bags.get(s).getContents().containsKey("shiny gold bag")
         || childrenContain(bags.get(s), "shiny gold bag", bags)){
        count++;
      } 
    }
    return count;
  }

  public static boolean childrenContain(Bag b, String target, HashMap<String, Bag> bags){
    if(b.getContents() != null){
      if(b.getContents().containsKey(target)){
        return true;
      } else {
        for(String s : b.getContents().keySet()){
          if(childrenContain(bags.get(s), target, bags)){
            return true;
          }
        }
      }
    }
    return false;
  }
}
