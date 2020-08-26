package Day01;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D1P2 {
    // Where we will store our fuel values
    private ArrayList<Integer> mass;
    

    public D1P2(String input){
        File file = new File(input);
        mass = new ArrayList<Integer>();
        parseInput(file);
    }
    
    // Parses our input file into an ArrayList
    private void parseInput(File file){
        try {
            Scanner in = new Scanner(file);

            while(in.hasNextInt()){
                    mass.add(in.nextInt());
            }
            in.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private int calcFuel(){
        int sum = 0;
        for(int i : mass){
            sum += fuelConv(i);
        }
        return sum;
    }

    private int fuelConv(int i){
        int val = i/3 - 2;
        // Recursively calculate fuel values until the results are below 0
        return (val > 0 ? (val + fuelConv(val)) : 0);
    }

    public static void main(String args[]){
        D1P2 d1p1 = new D1P2(args[0]);

        System.out.printf("You need %d units of Fuel\n", d1p1.calcFuel());
    }
}