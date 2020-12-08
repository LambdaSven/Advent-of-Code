package Day08;

import java.util.ArrayList;
import java.util.HashSet;

public class Bootcode {
  HashSet<Integer> visited = new HashSet<Integer>();
  Integer programCounter = 0, accumulator = 0;
  ArrayList<String> code;
  boolean infiniteLoop = false;

  public Bootcode(ArrayList<String> in){
    code = in;
  }

  public void run(){
    while(programCounter < code.size()){
      execute();
      if(visited.contains(programCounter)){
        infiniteLoop = true;
        break;
      }
    }
  }
  
  private void execute(){
    visited.add(programCounter);
    String[] temp = code.get(programCounter).split(" ");
    String opcode = temp[0];
    Integer operand = Integer.parseInt(temp[1]);
    switch(opcode){
      case "acc":
        accumulator += operand;
        programCounter++;
        break;
      case "jmp":
        programCounter += operand;
        break;
      case "nop":
        programCounter++;
        break;
    }
  }

  public Integer getAccumulator(){
    return accumulator;
  }

  public boolean terminates(){
    return !infiniteLoop;
  }
}
