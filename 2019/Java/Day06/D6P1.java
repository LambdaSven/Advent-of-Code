package Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class D6P1 {
    public static void main(String[] args){
        ArrayList<String> relationships = parseInput(args[0]);
        HashMap<String, Planet> map = createMap(relationships);
        System.out.printf("Total Number of Orbits is %d.\n", totalOrbits(map));
    }

    /**
     * We can get the total number of orbits by simply counting the number
     * of orbits away each Planet is from COM.
     * @param map - The HashMap of all our Planets
     * @return int - the total number of orbits in our map
     */
    private static int totalOrbits(HashMap<String, Planet> map){
        int count = 0;
        for(Planet p : map.values()){
            count += p.distanceTo("COM");
        }
        return count;
    }

    /**
     * Creates a hashmap of all the planets in our system,
     * with all necessary relationships between planets
     * @param r - The list of all relationships known
     * @return HashMap<String, Planet> representing our System
     */
    private static HashMap<String, Planet> createMap(ArrayList<String> r){
        HashMap<String, Planet> map = new HashMap<String, Planet>();

        /*
        * I chose to populate the HashMap first, because populating it 
        * and creating all the relationships would involve
        *   1) Have we already created the parent Planet
        *   2) If we haven't, we must create it
        *   3) Have we create the parent of the parent Planet?
        *   Repeat potentially until COM
        *
        * My solution is essentially the worst-case of that solution, but is 
        * simpler to understand and program. We traverse the list exactly twice, 
        * which means that our complexity is Θ(n)
        */

        //Populate the HashMap
        for(String s: r){
            String[] temp = s.split("\\)");
            String child = temp[1];
            map.put(child, new Planet(child));
        }

        //Create the relationships between Planets
        for(String s: r){
            String[] temp = s.split("\\)");
            String child = temp[1];
            String root = temp[0];
            //COM is never a child, so it needs to be created during this stage
            if(!root.equals("COM")){
                map.get(child).setParent(map.get(root));
            } else {
                map.get(child).setParent(new Planet("COM"));
            }
        }
        return map;
    }

    /**
     * Parse the input file into an ArrayList containing all relationships in our System
     * @param s Path to input File
     * @return ArrayList<String> 
     */
    private static ArrayList<String> parseInput(String s){
        ArrayList<String> r = new ArrayList<String>();
        File file = new File(s);
        try {
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                r.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return r;
    }
}
