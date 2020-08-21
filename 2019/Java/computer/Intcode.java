package computer;
import java.util.ArrayList;

public class Intcode {
    private ArrayList<Integer> tape;
    private int pc;

    //Direct load
    private int ld(int i){
        return tape.get(i);
    }

    //Indirect Load
    private int ild(int i){
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
        tape.set(ld(o+3), ild(o+2) + ild(o+1));
        pc += 4;
    }

    private void mul(int o){
        tape.set(ld(o+3), ild(o+2) * ild(o+1));
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