package Day07;

import java.io.File;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import aocutil.Utilities;

public class D7P2 {
  public static void main(String[] args){
    HashMap<String, Bag> bags = Utilities
                        .parseInputFile(new File(args[0]))
                        .stream()
                        .map(Bag::new)
                        .collect(Collectors.toMap(Bag::getId,
                                                  Function.identity(),
                                                  (k1, k2) -> k1,
                                                  HashMap::new));
    
    System.out.printf("Your bag contains %d other bags", part2(bags));
  }

  public static int part2(HashMap<String, Bag> bags){
    int count = 0;
    Bag root = bags.get("shiny gold bag");

    count += countChildren(bags, root, 1);
    return count;
  }

  public static int countChildren(HashMap<String, Bag> bags, Bag root, int mul){
    int count = 0;
    if(root.getContents() == null){
      return count;
    }
    for(String s : root.getContents().keySet()){
      count += mul*root.getContents().get(s);
      count += countChildren(bags, bags.get(s), mul*root.getContents().get(s));
    }
    return count;
  }
}
