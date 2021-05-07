package Day19;

import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
  HashMap<Integer, String> rules = new HashMap<Integer, String>();
  ArrayList<String> inputStrings = new ArrayList<String>();
  String regex = "";


  public Checker(ArrayList<String> in){
    for(String s : in){
      if(s.contains(":")){
        String[] temp = s.split(":");
        rules.put(Integer.parseInt(temp[0]), temp[1].trim());
      } else {
        inputStrings.add(s);
      }
    }
  }

  public void part2(){
    rules.put(8, "42 +");
    rules.put(11, "42 ( 42 ( 42 ( 42 ( 42 ( 42 31 ) ? 31 ) ? 31 ) ? 31 ) ? 31 ) ? 31");
  }

  public void compile(Integer n){
    System.out.println("Generating Regex ...");
    regex = "( " + rules.get(n) + " )";
    while(regex.matches(".*\\d.*")){
      String[] temp = regex.split(" ");
      for(String s : temp){
        if(!s.matches("\\D")){
          String replace = "";
          String rule = rules.get(Integer.parseInt(s));
          if(rule.contains("|")){
            replace = "( " + rule + " )";
          } else {
            replace = rule;
          }
          regex = regex.replaceAll(" " + s + " ", " " + replace + " ");
          regex = regex.replaceAll("\"", "");
        }
      }
    }
    regex = regex.replaceAll(" ", "");
    System.out.println(regex);
  }

  public long check(Integer n){
    compile(n);

    return inputStrings.stream()
                       .filter(e -> e.matches(regex))
                       .count();
  }
}
