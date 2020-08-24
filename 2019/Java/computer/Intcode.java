package computer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Intcode {
    private ArrayList<Integer> tape;
    private Queue<Integer> inBuf, outBuf;
    private int pc;
    private boolean isHalted;

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
        outBuf.add(x);
        pc += 2;
    }

    private void in(){
        while(inBuf.isEmpty()){
            //do nothing 
        }
        int s = inBuf.remove();
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

    public Intcode(ArrayList<Integer> in, int start){
        tape = clone(in);
        pc = start;
        inBuf = new LinkedList<Integer>();
        outBuf = new LinkedList<Integer>();
        isHalted = false;
    }

    public Intcode(ArrayList<Integer> in, int start, Integer[] inputs){
        tape = clone(in);
        pc = start;
        inBuf = new LinkedList<Integer>(Arrays.asList(inputs));
        outBuf = new LinkedList<Integer>();
        isHalted = false;
    }

    public Intcode(ArrayList<Integer> in, Integer[] inputs){
        tape = clone(in);
        pc = 0;
        inBuf = new LinkedList<Integer>(Arrays.asList(inputs));
        outBuf = new LinkedList<Integer>();
        isHalted = false;
    }

    private static ArrayList<Integer> clone(ArrayList<Integer> src){
        ArrayList<Integer> clone = new ArrayList<Integer>();
        for(int i: src){
            clone.add(i);
        }
        return clone;
    }

    public int getOutput(){
        return outBuf.remove();
    }

    public int getMemory(int i){
        return imm(i);
    }

    public boolean isHalted(){
        return isHalted;
    }

    public void input(Integer i){
        inBuf.add(i);
    }
}