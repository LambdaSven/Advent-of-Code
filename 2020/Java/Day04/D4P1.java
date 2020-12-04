package Day04;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D4P1 {
  public static void main(String[] args){
    ArrayList<String> input = Utilities.parseInputFile(new File(args[0]));
    ArrayList<Passport> passports = new ArrayList<Passport>();
    
    String collector = "";
    for(String s : input){
      if(!s.isEmpty()){
        collector = collector.concat(" " + s);
      } else {
        passports.add(new Passport(collector));
        collector = "";
      }
    }

    long validcount = passports.stream()
                               .filter(Passport::cheapValidation)
                               .count();

    System.out.printf("There are %d valid passports.\n", validcount);
  }
}
