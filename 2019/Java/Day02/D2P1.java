package Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import computer.Intcode;

public class D2P1 {
    public static void main(String[] args){
        ArrayList<Integer> tape = parseInput(new File(args[0]));
        tape.set(1, 12);
        tape.set(2, 2);
        Intcode computer = new Intcode(tape, 0);
        computer.run();
        System.out.printf("The value in position 0 is %d", computer.getMemory(0));
    }

    public static ArrayList<Integer> parseInput(File file){
        try {
            Scanner in = new Scanner(file);
            String[] temp = in.nextLine().split(",");
            ArrayList<Integer> al = new ArrayList<>(200000); // Allocate large amount of memory to computer
            in.close();

            for(String s : temp){
                al.add(Integer.parseInt(s));
            }
            return al;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}