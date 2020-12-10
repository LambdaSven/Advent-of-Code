package Day10;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import aocutil.Utilities;

public class D10P1 {
  public static void main(String[] args){
    ArrayList<Integer> adapters = Utilities.parseToInts(new File(args[0]));
    adapters.add(0); // add the source joltage
    adapters.sort(Comparator.naturalOrder());
    adapters.add(adapters.get(adapters.size()-1) + 3);// add device joltage

    int single = 0, triple = 0;
    for(int i = 0; i < adapters.size()-1; i++){
      if(adapters.get(i+1).equals(adapters.get(i) + 1)){
        single++;
      }
      if(adapters.get(i+1).equals(adapters.get(i) + 3)){
        triple++;
      }
    }

    System.out.printf("The product of adapter joltage is %d * %d = %d\n", single, triple, single*triple);
  }
}
