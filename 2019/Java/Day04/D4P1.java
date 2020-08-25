package Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D4P1 {
    /* 
    * Notice - For this program we are counting digits of a number D₀D₁D₂...Dₙ
    * This is contrary to the standard DₙDₙ₋₁...D₀. We are doing it this way simply 
    * so that when we store the digits in an array, the numbers are not stored backwards
    * That is, instead of {Dₙ, ₙ₋1, ...,D₀} we store them as {D₀, D₁, ..., Dₙ}
    */
    public static void main(String[] args){
        int[] range = parseInput(args[0]);
        int count = countValidPasswords(range[0], range[1]);
        System.out.printf("There are %d valid passwords \n", count);
    }

    /**
     * Counts the number of valid passwords between two input values
     * Valid passwords must meet 2 conditions
     *  1) They must have sequential digits (D₀ ≥ D₁ ≥ D₂ ≥ D₃ ≥ D₄ ≥ D₅)
     *  2) They must have duplicates (Dₓ = Dₓ₊₁)
     * @param start - The starting value
     * @param end - The ending value
     * @return int - The number of valid passwords
     */
    private static int countValidPasswords(int start, int end){
        int count = 0;
        for(int i = start; i < end; i++){
            int[] digits = split(i);
            //Check condition 1 and 2
            if(areSequential(digits) && hasDoubles(digits)){
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if the given number has any duplicated digits (Dₓ = Dₓ₊₁)
     * @param digits - The number as an int[] of digits
     * @return boolean Any Dₓ = Dₓ₊₁
     */
    private static boolean hasDoubles(int[] digits){
        boolean out = false;
        for(int i = 0; i < digits.length-1; i++){
            if(digits[i] == digits[i+1]){
                out = true;
            }
        }
        return out;
    }

    /**
     * Checks if the number has all sequential digits (D₀ ≥ D₁ ≥ D₂ ≥ D₃ ≥ D₄ ≥ D₅)
     * @param digits - The number represented as an int[] if digits
     * @return boolean (D₀ ≥ D₁ ≥ D₂ ≥ D₃ ≥ D₄ ≥ D₅)
     */
    private static boolean areSequential(int[] digits){
        boolean out = true;
        for(int i = 0; i < digits.length-1; i++){
            if(digits[i] > digits[(i + 1)]){
                out = false;
            }
        }
        return out;
    }

    /**
     * Splits a 6 digit number into an int[] where each element is a seperate digit
     * {x₀, x₁, x₂, x₃, x₄, x₅} = {D₀, D₁, D₂, D₃, D₄, D₅}
     * @param digits - The number to be split
     * @return int[] representing the digits
     */
    private static int[] split(int num){
        int[] dig = new int[6]; //Input will always be 6 digits
        //The array works backwards because we want dig[0] to be the MSB, backwards from traditional notation
        for(int i = 5, div = 1; i >= 0; i--, div *= 10){
            dig[i] = num/div % 10;
        }
        return dig;
    }

    /**
     * Parses the input into an int[] containing the range of passwords to search
     * @param s - The file path
     * @return int[] containing the start and end point of the range
     */
    private static int[] parseInput(String s){
        File file = new File(s);
        int[] out = new int[2];
        try{
            Scanner input = new Scanner(file);
            String[] temp = input.nextLine().split("-"); 
            input.close();
            out[0] = Integer.parseInt(temp[0]);
            out[1] = Integer.parseInt(temp[1]);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return out;
    }
}