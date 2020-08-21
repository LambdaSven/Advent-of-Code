package Day01;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class D1P1 {
    private ArrayList<Integer> mass;
    
    // Constructor parses our input file into the data we need.
    public D1P1(String input){
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

    // Caulcuate the sum of Fuel across all values in mass
    private int calcFuel(){
        int sum = 0;
        for(int i : mass){
            sum += fuelConv(i);
        }
        return sum;
    }
    // Perform the simple formula
    private int fuelConv(int i){
        return i/3 - 2;
    }

    public static void main(String args[]){
        D1P1 d1p1 = new D1P1(args[0]);

        System.out.printf("You need %d units of Fuel\n", d1p1.calcFuel());
    }
}