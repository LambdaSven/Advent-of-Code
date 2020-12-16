package Day16;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ticket {
  //used in part 1
  HashSet<Integer> valid = new HashSet<Integer>();
  ArrayList<ArrayList<Integer>> tickets = new ArrayList<ArrayList<Integer>>();
  //used in part 2
  ArrayList<ArrayList<Integer>> validTickets = new ArrayList<ArrayList<Integer>>();
  HashMap<String, HashSet<Integer>> validMap = new HashMap<String, HashSet<Integer>>();


  public Ticket(ArrayList<String> in){
    Pattern p = Pattern.compile(": \\d");
    for(String s : in){
      if(p.matcher(s).find()){
        String[] split = s.split(":");
        String temp = split[1];
        String name = split[0];

        String[] values = temp.split(" or ");
        String[] set1 = values[0].split("-");
        String[] set2 = values[1].split("-");

        HashSet<Integer> t = new HashSet<Integer>();
        IntStream.range(Integer.parseInt(set1[0].trim()), Integer.parseInt(set1[1].trim())+1).forEach(e -> t.add(e));
        IntStream.range(Integer.parseInt(set2[0].trim()), Integer.parseInt(set2[1].trim())+1).forEach(e -> t.add(e));
      
        validMap.put(name, t);
        valid.addAll(t);

      } else if(s.contains(",")) {
        String[] temp = s.split(",");
        tickets.add(Arrays.stream(temp).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new)));
      }
    }
    for(ArrayList<Integer> e : tickets){
      if(e.stream().filter(n -> !valid.contains(n)).count() == 0){
        validTickets.add(e);
      }
    }
  }

  //part 2
  public BigInteger departureMul(){
    BigInteger prod = BigInteger.ONE;
    
    HashMap<Integer, Set<String>> poss = new HashMap<Integer, Set<String>>();

    for(int i = 0; i < validTickets.get(0).size(); i++){
      Set<String> fields = validMap.keySet().stream().collect(Collectors.toSet());

      for(int j = 0; j < validTickets.size(); j++){
        for(String s : validMap.keySet()){
          if(!validMap.get(s).contains(validTickets.get(j).get(i))){
            fields.remove(s);
          }
        }
      }
      poss.put(i, fields);
    }

    for(int i = 0; i < 6;){
      for(int e : poss.keySet()){
        if(poss.get(e).size() == 1){
          String onlyoption = poss.get(e).iterator().next();
          for(int e2 : poss.keySet()){
            poss.get(e2).remove(onlyoption);
          }

          if(onlyoption.contains("departure")){
            prod = prod.multiply(BigInteger.valueOf(validTickets.get(0).get(e)));
            i++;
          }
        }
      }
    }
    return prod;
  }


  //part 1
  public long errorRate(){
    return tickets.stream()
                  .flatMap(e -> e.stream())
                  .filter(e -> !valid.contains(e))
                  .reduce(0, Integer::sum);
  }
}
