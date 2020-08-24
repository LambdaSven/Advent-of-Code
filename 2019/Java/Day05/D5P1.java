package Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import computer.Intcode;

public class D5P1 {
    public static void main(String[] args){
        ArrayList<Integer> tape = parseInput(new File(args[0]));
        Intcode computer = new Intcode(tape, 0);
        computer.run();
    }

    public static ArrayList<Integer> parseInput(File file){
        try {
            Scanner in = new Scanner(file);
            String[] temp = in.nextLine().split(",");
            ArrayList<Integer> al = new ArrayList<>(); 
            in.close();

            for(String s : temp){
                al.add(Integer.parseInt(s));
            }
            for(int i = 0; i < 20000; i++){
                al.add(i);// Allocate large amount of memory to computer
            }
            return al;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}