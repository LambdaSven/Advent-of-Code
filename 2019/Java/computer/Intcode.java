package computer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Intcode Computer used for many problems in AOC 2019. 
 */
public class Intcode {
  private ArrayList<Integer> tape;
  private Queue<Integer> inBuf, outBuf;
  private int pc;
  private boolean isHalted;

  /**
   * Parses the input at the current position and executes the command
   * The instructions themselves increment the Program Counter
   */
  public void run(){
    if(isHalted){return;}
    while(tape.get(pc) != 99 && pc < tape.size()){
      switch(tape.get(pc) % 100){ // last 2 digits are instruction
        case 1:
          add(getModes(pc));
          break;
        case 2:
          mul(getModes(pc));
          break;
        case 3:
          in();
          break;
        case 4:
          out(getModes(pc));
          return;
        case 5:
          jt(getModes(pc));
          break;
        case 6:
          jf(getModes(pc));
          break;
        case 7:
          lt(getModes(pc));
          break;
        case 8:
          eq(getModes(pc));
      }
    }
    isHalted = true;
  }

  /**
   * Evaluates if two values are equal, storing 1 or 0 into 
   * the requested address based on equality
   * @param modes The addressing modes of the paramaters
   */
  private void eq(int[] modes){
    if(load(pc + 1, modes[0]) == load(pc+2, modes[1])){
      store(imm(pc+3), 1);
    } else {
      store(imm(pc+3), 0);
    }
    pc += 4;
  }
  
  /**
   * Evaluates if P₁ < P₂, storing 1 or 0 into 
   * the requested address based on the result
   * @param modes The addressing modes of the paramaters
   */
  private void lt(int[] modes){
    if(load(pc + 1, modes[0]) < load(pc+2, modes[1])){
      store(imm(pc+3), 1);
    } else {
      store(imm(pc+3), 0);
    }
    pc += 4;
  }

  /**
   * Moves to Program counter to the specified location
   * if P₁ = 0
   * @param modes The addressing modes of the paramaters
   */
  private void jf(int[] modes){
    if(load(pc+1, modes[0]) == 0){
      int d = load(pc+2, modes[1]);
      pc = d;
    } else {
      pc += 3;
    }
  }

  /**
   * Moves to Program counter to the specified location
   * if P₁ ≠ 0
   * @param modes The addressing modes of the paramaters
   */
  private void jt(int[] modes){
    if(load(pc+1, modes[0]) != 0){
      int d = load(pc+2, modes[1]);
      pc = d;
    } else {
      pc += 3;
    }
  }

  /**
   * Retrives the paramater modes from the instruction
   * {0, 1, 2} are the Modes for {P₁, P₂, P₃}
   * @param m the instruction being passed
   * @return the paramater Modes
   */
  private int[] getModes(int m){
    m = imm(m);
    int[] out = new int[3];
    m /= 100; // strip last two digits
    out[0] = m % 10; //param 1
    out[1] = m / 10 % 10; //param 2
    out[2] = m / 100 % 10; //param 3
    return out;
  }

  /**
   * Outputs the value stored at P₁ to the output buffer 
   * @param modes The addressing modes of the paramaters
   */
  private void out(int[] modes){
    int x;
    x = load(pc + 1, modes[0]);
    outBuf.add(x);
    pc += 2;
  }

  /**
   * Reads input from the input buffer and stores it into memory
   */
  private void in(){
    int s;
    s = inBuf.remove();
    int d = imm(pc + 1);
    store(d, s);
    pc += 2;
  }

  /**
   * Adds P₁ + P₂ and stores the result in P₃
   * @param modes The addressing modes of the paramaters
   */
  private void add(int[] modes){
    int a = load(pc+1, modes[0]);
    int b = load(pc+2, modes[1]);
    int d = imm(pc+3);
    store(d, a + b);
    pc += 4;
  }

  /**
   * Multiplies P₁ * P₂ and stores the result in P₃
   * @param modes The addressing modes of the paramaters
   */
  private void mul(int[] modes){
    int a = load(pc+1, modes[0]);
    int b = load(pc+2, modes[1]);
    int d = imm(pc+3);
    store(d, a * b);
    pc += 4;
  }

  /**
   * Returns the required data based on the passed mode
   *  0 - Direct Mode
   *  1 - Immediate Mode
   * @param a - The Parameter being handled
   * @param m - The mode of a
   * @return The required value from the parameter
   */
  private int load(int a, int m){
    if(m == 0){
      return dir(a);
    } else {
      return imm(a);
    }
  }

  /**
   * Immediate Load - returns the literal value stored at the index
   * @param i - The tape Index
   * @return tape[i]
   */
  private int imm(int i){
    return tape.get(i);
  }
  
  /**
   * Direct Load - Returns the value stored in the address referred to by the index
   * @param i - The index
   * @return tape[tape[i]];
   */
  private int dir(int i){
    return tape.get(imm(i));
  }    

  /**
   * Store the data in s to the location d
   * @param d - The destination address
   * @param s - The source Data
   */
  private void store(int d, int s){
    tape.set(d, s);
  }

  /**
   * Create a clone of the input tape (So that the original is intact),
   * Initilizes the I/O buffers, and sets isHalted to false
   * @param in - The input tape
   */
  public Intcode(ArrayList<Integer> in, Integer[] inputs){
    tape = clone(in);
    isHalted = false;
    outBuf = new LinkedList<Integer>();
    inBuf = new LinkedList<Integer>(Arrays.asList(inputs));
  }

  /**
   * Used to clone our input tape so that modifications in this
   * machine's state don't effect our source code
   * @param src - The source Tape
   * @return ArrayList<Integer> a clone of the input tape
   */
  private static ArrayList<Integer> clone(ArrayList<Integer> src){
    ArrayList<Integer> clone = new ArrayList<Integer>();
    for(int i: src){
      clone.add(i);
    }
    return clone;
  }

  /**
   * Returns the front of the output Buffer
   * @return - the from of the output buffer
   */
  public int getOutput(){
    return outBuf.remove();
  }

  /**
   * Returns the value stored in a particular address
   * @param i - the address to retrieve data from
   * @return the data stored at tape[i]
   */
  public int getMemory(int i){
    return imm(i);
  }

  /**
   * Returns if the machine has halted
   * @return state of isHalted
   */
  public boolean isHalted(){
    return isHalted;
  }

  /**
   * Adds the passed integer into the input buffer
   * @param i - Value to add to input buffer
   */
  public void input(Integer i){
    inBuf.add(i);
  }


}