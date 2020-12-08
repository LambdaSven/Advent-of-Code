package Day08;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D8P2 {
  public static void main(String[] args){
    ArrayList<String> code = Utilities.parseInputFile(new File(args[0]));

    ArrayList<Bootcode> computers = new ArrayList<Bootcode>();
    computers.add(new Bootcode(code));

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

    computers.parallelStream()
             .forEach(Bootcode::run);
    
    Bootcode success = computers.stream()
                                .filter(Bootcode::terminates)
                                .findFirst()
                                .orElse(new Bootcode(null));

    System.out.printf("The value of the accumulator at halting time is %d\n", success.getAccumulator());
  }

  public static ArrayList<String> deepCopy(ArrayList<String> src){
    ArrayList<String> out = new ArrayList<String>();
    for(String s : src){
      out.add(s);
    }
    return out;
  }
}
