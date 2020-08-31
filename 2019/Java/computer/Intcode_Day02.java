//This version is only kept for reference, all programs will use Intcode.java - The most up to date version
package computer;
import java.util.ArrayList;

public class Intcode_Day02 {
  private ArrayList<Integer> tape;
  private int pc;

  /**
   * Immediate Load - returns the value stored at the tape address
   */
  private int imm(int i){
    return tape.get(i);
  }

  /**
   * Direct load - Returns the value at the address referred to by i
   */
  private int dir(int i){
    return tape.get(tape.get(i));
  }

  /**
   * Runs until opcode 99 encountered or pc moves beyond end of tape
   */
  public void run(){
    while(tape.get(pc) != 99 && pc < tape.size()){
      switch(tape.get(pc)){
        case 1:
          add();
          break;
        case 2:
          mul();
          break;
      }
    }
  }

  /**
   * Adds P₁ + P₂ and stores the result in P₃
   * @param modes The addressing modes of the paramaters
   */
  private void add(){
    tape.set(imm(pc+3), dir(pc+2) + dir(pc+1));
    pc += 4;
  }

  /**
   * Multiplies P₁ * P₂ and stores the result in P₃
   * @param modes The addressing modes of the paramaters
   */
  private void mul(){
    tape.set(imm(pc+3), dir(pc+2) * dir(pc+1));
    pc += 4;
  }


  /**
   * Basic constructor, initilizes computer
   */
  public Intcode_Day02(ArrayList<Integer> in){
    tape = in;
    pc = 0;
  }

  /**
   * Returns the value stored in a particular address
   * @param i - the address to retrieve data from
   * @return the data stored at tape[i]
   */
  public int getMemory(int i){
    return tape.get(i);
  }
}