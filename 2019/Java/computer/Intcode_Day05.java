//This version is only kept for reference, all programs will use Intcode.java - The most up to date version
package computer;

import java.util.ArrayList;
import java.util.Scanner;


public class Intcode_Day05 {
  private ArrayList<Integer> tape;
  private int pc;
  Scanner input;

  public void run(){
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
          break;
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
  }

  private void eq(int[] modes){
    if(load(pc + 1, modes[0]) == load(pc+2, modes[1])){
      store(imm(pc+3), 1);
    } else {
      store(imm(pc+3), 0);
    }
    pc += 4;
  }
  
  private void lt(int[] modes){
    if(load(pc + 1, modes[0]) < load(pc+2, modes[1])){
      store(imm(pc+3), 1);
    } else {
      store(imm(pc+3), 0);
    }
    pc += 4;
  }

  private void jf(int[] modes){
    if(load(pc+1, modes[0]) == 0){
      int d = load(pc+2, modes[1]);
      pc = d;
    } else {
      pc += 3;
    }
  }

  private void jt(int[] modes){
    if(load(pc+1, modes[0]) != 0){
      int d = load(pc+2, modes[1]);
      pc = d;
    } else {
      pc += 3;
    }
  }

  private int[] getModes(int m){
    m = imm(m);
    int[] out = new int[3];
    m /= 100; // strip last two digits
    out[0] = m % 10;
    out[1] = m / 10 % 10;
    out[2] = m / 100 % 10;
    return out;
  }

  private void out(int[] modes){
    int x;
    x = load(pc + 1, modes[0]);
    System.out.println(x);
    pc += 2;
  }

  private void in(){
    System.out.print("Awaiting Input: ");
    int s = input.nextInt();
    int d = imm(pc + 1);
    store(d, s);
    pc += 2;
  }

  private void add(int[] modes){
    int a = load(pc+1, modes[0]);
    int b = load(pc+2, modes[1]);
    int d = imm(pc+3);
    store(d, a + b);
    pc += 4;
  }

  private void mul(int[] modes){
    int a = load(pc+1, modes[0]);
    int b = load(pc+2, modes[1]);
    int d = imm(pc+3);
    store(d, a * b);
    pc += 4;
  }

  // load with mode switching
  private int load(int a, int m){
    if(m == 0){
      return dir(a);
    } else {
      return imm(a);
    }
  }

  //Immediate Load
  private int imm(int i){
    return tape.get(i);
  }
  
  //Direct Load
  private int dir(int i){
    return tape.get(imm(i));
  }    

  // Store in Destination Address the data from S.
  private void store(int d, int s){
    tape.set(d, s);
  }

  public Intcode_Day05(ArrayList<Integer> in, int start){
    tape = in;
    pc = start;
    input = new Scanner(System.in);
  }

  public int getMemory(int i){
    return imm(i);
  }
}