package Day08;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D8P2 {
  public static void main(String[] args){
    ArrayList<String> code = Utilities.parseInputFile(new File(args[0]));

    ArrayList<Bootcode> computers = new ArrayList<Bootcode>();
    computers.add(new Bootcode(code));

    //Generate all computers
    for(int i = 0; i < code.size(); i++){
      if(code.get(i).contains("jmp")){
        String opcode = code.get(i);
        code.set(i, opcode.replace("jmp", "nop"));
        computers.add(new Bootcode(deepCopy(code)));
        code.set(i, opcode);

      } else if(code.get(i).contains("nop")){
        String opcode = code.get(i);
        code.set(i, opcode.replace("nop", "jmp"));
        computers.add(new Bootcode(deepCopy(code)));
        code.set(i, opcode);
      }
    }

    Bootcode success = computers.parallelStream() // this procedure is embarrassingly parallel, so no concerns here
                                .peek(e -> e.run()) //run the computesr
                                .filter(Bootcode::terminates) // find the one that terminates
                                .findFirst() // convert single length stream into value
                                .orElse(new Bootcode(null)); // in case findFirst() fails

    System.out.printf("The value of the accumulator at halting time is %d\n", success.getAccumulator());
  }

  // Java is pass by reference - gotta make a deep copy
  public static ArrayList<String> deepCopy(ArrayList<String> src){
    ArrayList<String> out = new ArrayList<String>();
    for(String s : src){
      out.add(s);
    }
    return out;
  }
}
