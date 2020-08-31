package Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import computer.Intcode_Day07;

public class D7P2 {
  public static void main(String[] args){
    ArrayList<Integer> tape = parseInput(new File(args[0]));
    int max = 0;

    for(int i = 56789; i < 98765; i++){
      if(validState(i)){
        int[] digits = splitDigits(i);
        //Init all computers
        Intcode_Day07 computer1 = new Intcode_Day07(tape, new Integer[]{digits[0]});
        Intcode_Day07 computer2 = new Intcode_Day07(tape, new Integer[]{digits[1]});
        Intcode_Day07 computer3 = new Intcode_Day07(tape, new Integer[]{digits[2]});
        Intcode_Day07 computer4 = new Intcode_Day07(tape, new Integer[]{digits[3]});
        Intcode_Day07 computer5 = new Intcode_Day07(tape, new Integer[]{digits[4]});

        Integer pipe = 0;
        // We loop until any of the machines halt, passing the output from each stage into the next
        while(true){
          computer1.input(pipe);
          computer1.run();
          if(computer1.isHalted()){break;}
          pipe = computer1.getOutput();

          computer2.input(pipe);
          computer2.run();
          if(computer2.isHalted()){break;}
          pipe = computer2.getOutput();

          computer3.input(pipe);
          computer3.run();
          if(computer3.isHalted()){break;}
          pipe = computer3.getOutput();

          computer4.input(pipe);
          computer4.run();
          if(computer4.isHalted()){break;}
          pipe = computer4.getOutput();

          computer5.input(pipe);
          computer5.run();
          if(computer5.isHalted()){break;}
          pipe = computer5.getOutput();
        }

        int out = pipe;
        if(out > max){max = out;}
      }
    }

    System.out.printf("The Maximum signal that can be sent is %d\n", max);
  }

  /**
   * Splits the given num into it's digits
   * @param num - the number to be split
   * @return an int[] such that {0, 1, 2, 3, 4} = {D₀, D₁, D₂, D₃, D₄}
   */
  private static int[] splitDigits(int num){
    int[] digits = new int[5];
    for(int i = 4, div = 1; i >= 0; i--, div *= 10){
      digits[i] = num/div % 10;
    }
    return digits;
  }

  /**
   * A state is valid if D₀ ≠ D₁ ≠ D₂ ≠ D₃ ≠ D₄
   * @param state - The state to be checked
   * @return D₀ ≠ D₁ ≠ D₂ ≠ D₃ ≠ D₄
   */
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

  /**
   * This function parses our input file into the necessary format for use with 
   * the Intcode_Day07 class
   * @param file
   * @return ArrayList<Integer> - Our instruction Tape
   */
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