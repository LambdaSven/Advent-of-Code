package Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D4P2 {
    public static void main(String[] args){
        int[] range = parseInput(args[0]);
        int count = countValidPasswords(range[0], range[1]);
        System.out.printf("There are %d valid passwords \n", count);
    }

    private static int countValidPasswords(int start, int end){
        int count = 0;
        for(int i = start; i < end; i++){
            int[] digits = split(i);
            if(areSequential(digits) && hasOnlyDoubles(digits)){
                count++;
            }
        }
        return count;
    }

    private static boolean hasOnlyDoubles(int[] digits){
        boolean out = false;
        for(int i = 0; i < digits.length-1; i++){
            if(digits[i] == digits[i+1]){
                out = true;
                if(i + 2 < digits.length && digits[i+2] == digits[i]){
                    out = false;
                }
                if(i - 1 >= 0 && digits[i-1] == digits[i]){
                    out = false;
                }
                if(out){return out;}
            }
        }
        return out;
    }

    private static boolean areSequential(int[] digits){
        boolean out = true;
        for(int i = 0; i < digits.length-1; i++){
            if(digits[i] > digits[(i + 1)]){
                out = false;
            }
        }
        return out;
    }

    private static int[] split(int num){
        int[] dig = new int[6]; //Input will always be 6 digits
        for(int i = 5, div = 1; i >= 0; i--, div *= 10){
            dig[i] = num/div % 10;
        }
        return dig;
    }

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