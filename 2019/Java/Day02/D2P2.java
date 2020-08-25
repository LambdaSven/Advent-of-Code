package Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import computer.Intcode;

public class D2P2 {
    public static void main(String[] args){
        ArrayList<Integer> tape = parseInput(new File(args[0]));
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                ArrayList<Integer> clone = clone(tape);
                clone.set(1, i);
                clone.set(2, j);
                Intcode computer = new Intcode(clone, 0);
                computer.run();
                if(computer.getMemory(0) == 19690720){
                    System.out.printf("%d\n", 100 * i + j);
                    System.exit(0);
                }
            }
        }
        System.out.printf("Could not solve");
    }
    /**
     * We create our own method of cloning our tapes,
     * because the ArrayList.clone() method return Object
     * And we don't want to perform a type conversion
     * 
     * @param tape - the original tape
     * @return ArrayList<Integer> - The cloned copy of the tape
     */
    private static ArrayList<Integer> clone(ArrayList<Integer> tape) {
        ArrayList<Integer> clone = new ArrayList<Integer>();
        for(Integer i : tape){
            clone.add(i);
        }
        return clone;
    }

    /**
     * This function parses our input file into the necessary format for use with 
     * the Intcode class
     * @param file
     * @return ArrayList<Integer> - Our instruction Tape
     */
    public static ArrayList<Integer> parseInput(File file) {
        try {
            Scanner in = new Scanner(file);
            String[] temp = in.nextLine().split(",");
            ArrayList<Integer> al = new ArrayList<>(200000); // Allocate large memory
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