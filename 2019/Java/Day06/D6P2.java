package Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

//Reuse Planet Definition
public class D6P2 {
    public static void main(String[] args){
        ArrayList<String> relationships = parseInput(args[0]);
        HashMap<String, Planet> map = createMap(relationships);
        System.out.printf("Orbital Transfers Required is %d\n", getTransfers(map, "YOU", "SAN"));
    }

    /**
     * To get the number of transfers between two systems, we can simply walk up
     * each system until we meet, and add the distances between our starting system and where we meet
     * @param map - The HashMap representing our System
     * @param s - The starting Planet
     * @param d - The Destination Planet
     * @return The number of transfers between planets
     */
    private static int getTransfers(HashMap<String, Planet> map, String s, String d){
        ArrayList<String> sTree = map.get(s).getTree("COM");
        ArrayList<String> dTree = map.get(d).getTree("COM");

        //We need to traverse the list starting at COM
        Collections.reverse(sTree);
        Collections.reverse(dTree);
        String div = findDivergence(sTree, dTree);

        /*
        * Because we are already in orbit of our parent, we do not need to count it
        * in our number of transfers, so we can calcuate the distance from our parent
        * to the point of divergence
        */
        return (map.get(s).getParent().distanceTo(div) + map.get(d).getParent().distanceTo(div));
    }

    /**
     * We walk through a list of Strings until the strings diverge (s₁ ≠ s₂)
     * And we return the point of divergence
     * @param s - The first List of Strings
     * @param d - The second list of Strings
     * @return the point of Divergence
     */
    private static String findDivergence(ArrayList<String> s, ArrayList<String> d){
        String out = s.get(0);
        for(int i = 0; s.get(i) == d.get(i); i++){
            out = s.get(i);
        }
        return out;
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

        // Populate the hashmap
        for(String s: r){
            String[] temp = s.split("\\)");
            String child = temp[1];
            map.put(child, new Planet(child));
        }

        // Create the relationships
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