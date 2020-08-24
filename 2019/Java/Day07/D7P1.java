package Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import computer.Intcode;

public class D7P1 {
    public static void main(String[] args){
        ArrayList<Integer> tape = parseInput(new File(args[0]));
        int max = 0;

        for(int i = 12345; i < 54321; i++){
            if(validState(i)){
                int[] digits = splitDigits(i);
                Integer[] in = {digits[0], 0};
                Intcode computer1 = new Intcode(tape, in);
                computer1.setPipeMode(true);
                computer1.setInBuf(true);
                computer1.setOutBuf(true);
                computer1.run();

                in[0] = digits[1];
                in[1] = computer1.getOutput();
                Intcode computer2 = new Intcode(tape, in);
                computer2.setPipeMode(true);
                computer2.setInBuf(true);
                computer2.setOutBuf(true);
                computer2.run();

                in[0] = digits[2];
                in[1] = computer2.getOutput();
                Intcode computer3 = new Intcode(tape, in);
                computer3.setPipeMode(true);
                computer3.setInBuf(true);
                computer3.setOutBuf(true);
                computer3.run();

                in[0] = digits[3];
                in[1] = computer3.getOutput();
                Intcode computer4 = new Intcode(tape, in);
                computer4.setPipeMode(true);
                computer4.setInBuf(true);
                computer4.setOutBuf(true);
                computer4.run();

                in[0] = digits[4];
                in[1] = computer4.getOutput();
                Intcode computer5 = new Intcode(tape, in);
                computer5.setPipeMode(true);
                computer5.setInBuf(true);
                computer5.setOutBuf(true);
                computer5.run();

                int out = computer5.getOutput();

                if(out > max){max = out;}
                
            }
        }

        System.out.printf("The Maximum signal that can be sent is %d\n", max);
    }

    private static int[] splitDigits(int num){
        int[] digits = new int[5];
        for(int i = 4, div = 1; i >= 0; i--, div *= 10){
            digits[i] = num/div % 10;
        }
        return digits;
    }

    private static boolean validState(int state){
        int[] digits = splitDigits(state);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(i != j && digits[i] == digits[j]){
                    return false;
                }
            }
        }
        return true;
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