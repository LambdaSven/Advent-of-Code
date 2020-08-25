package Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class D3P2 {
    public static void main(String[] args){
        String[] wires = parseInput(args[0]);
        String w1 = wires[0];
        String w2 = wires[1];
        HashMap<String, Integer> wire = drawWire(w1); 

        ArrayList<String> intersections = getIntersections(wire, w2);

        int shortestInt = getShortest(intersections, wire);

        System.out.printf("The Closest Intersection is %d units away\n", shortestInt);

    }


    /**
     * This function will calucuate the intersection that has the shortest round trip from (0, 0) back to (0, 0)
     * (0, 0) → (x, y) via wire1 + (0, 0) → (x, y) via wire2
     * 
     * @param intersection - The list of intersections
     * @param wire1 - The mapping of the first Wire  
     * @return int - The shortest round trip
     */
    private static int getShortest(ArrayList<String> intersections, HashMap<String, Integer> wire1){
        int shortest = Integer.MAX_VALUE;
        for(String s : intersections){
            int temp = wire1.get(s);
            if(temp < shortest){shortest = temp;}
        }
        return shortest;
    }

    /**
     * This function searching through the first wire, compares each point with
     * a point on the second wire, adding the point to a list of intersections.
     * 
     * @param w1 HashMap<String, Integer> - Our first Wire
     * @param w2 String - Our second wire in String representation
     * @return ArrayList<String> - List of all intersections between the two Wires.
     */
    private static ArrayList<String> getIntersections(HashMap<String, Integer> w1, String w2){
        int x = 0; int y = 0, d = 0;
        ArrayList<String> ints = new ArrayList<String>();
        for(String s : w2.split(",")){
            char dir = s.charAt(0);
            int len = Integer.parseInt(s.substring(1));
            int[] delta = getDir(dir);
            for(int i = 0; i < len; i++){
                x += delta[0];
                y += delta[1];
                d++;
                if(w1.containsKey(x + ":" + y)) {
                    ints.add(x + ":" + y);
                    // We change the distance from (0, 0) to include the distance back along the second wire.
                    w1.put(x + ":" + y, w1.get(x + ":" + y) + d);
                }
            }
        }
        return ints;
    }

    /**
     * This function adds all points on a line to a HashMap, for easy lookup
     * The values of each point will be the distance from (0, 0)
     * 
     * @param in String - Our first Wire
     * @return HashMap<String, Integer> - The mapping of the input Wire
     */
    private static HashMap<String, Integer> drawWire(String in){
        HashMap<String, Integer> wire = new HashMap<String, Integer>();
        int x = 0, y = 0, d = 0;
        //For each instruction in our input list
        for(String s : in.split(",")){
            // Get the direction character
            char dir = s.charAt(0);
            // Get the length
            int len = Integer.parseInt(s.substring(1));
            // parse the direction into {Δx, Δy}
            int[] delta = getDir(dir);
            // For the entire length of the segment
            for(int i = 0; i < len; i++){
                x += delta[0];
                y += delta[1];
                //Add the point to the HashMap, incrementing the distance from (0, 0)
                wire.put(x + ":" + y, ++d);
            }
        }
        return wire;
    }

    /**
     * We can represent directions as {Δx, Δy}, and this representation will make
     * math in drawWire very simple.
     * 
     * @param c - The direction of the wire 
     * @return int[] - {Δx, Δy} required.
     */
    private static int[] getDir(char c){
        switch(c){
            case 'R': return new int[] {1, 0};
            case 'L': return new int[] {-1, 0};
            case 'U': return new int[] {0, -1};
            case 'D': return new int[] {0, 1};
            default: return null;
        }
    }

    /**
     * Parse the input into two seperate wires.
     * @param s - The input file path
     * @return String[] containing our two wires.
     */
    private static String[] parseInput(String s){
        File file = new File(s);
        try {
            Scanner in = new Scanner(file);
            String[] out = new String[2];
            out[0] = in.nextLine();
            out[1] = in.nextLine();
            //Don't forget to close the scanner
            in.close();
            return out;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}