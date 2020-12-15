package Day15;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
  int prev;
  HashMap<Integer, ArrayList<Integer>> memory = new HashMap<Integer, ArrayList<Integer>>(); //number, turn said last

  public Game(ArrayList<String> in){
    String[] t = in.get(0).split(",");

    for(int i = 0; i < t.length; i++){
      ArrayList<Integer> temp = new ArrayList<Integer>();
      temp.add(i);
      temp.add(i);
      memory.put(Integer.parseInt(t[i]), temp);
    }
    prev = Integer.parseInt(t[t.length-1]);
  }

  public void run(int n){
    for(int i = memory.size(); i < n; i++){
      ArrayList<Integer> cur = memory.get(prev);
      
      int diff = cur.get(cur.size()-1) - cur.get(cur.size()-2);
      
      prev = diff;
      
      if(memory.containsKey(diff)){
        memory.get(diff).add(i);
      } else {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(i);
        temp.add(i);
        memory.put(diff, temp);
      }
    }
  }
  
  public int getNum(){
    return prev;
  }
}
