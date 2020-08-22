package computer;
import java.util.ArrayList;

public class Intcode {
    private ArrayList<Integer> tape;
    private int pc;

    //Immediate Load
    private int imm(int i){
        return tape.get(i);
    }

    //Direct Load
    private int dir(int i){
        return tape.get(tape.get(i));
    }

    public void run(){
        while(tape.get(pc) != 99 && pc < tape.size()){
            switch(tape.get(pc)){
                case 1:
                    add(pc);
                    break;
                case 2:
                    mul(pc);
                    break;
            }
        }
    }

    private void add(int o){
        tape.set(imm(o+3), dir(o+2) + dir(o+1));
        pc += 4;
    }

    private void mul(int o){
        tape.set(imm(o+3), dir(o+2) * dir(o+1));
        pc += 4;
    }


    public Intcode(ArrayList<Integer> in, int start){
        tape = in;
        pc = start;
    }

    public int getMemory(int i){
        return tape.get(i);
    }
}