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

    private static int getShortest(ArrayList<String> intersections, HashMap<String, Integer> wire1){
        int shortest = Integer.MAX_VALUE;
        for(String s : intersections){
            int temp = wire1.get(s);
            if(temp < shortest){shortest = temp;}
        }
        return shortest;
    }

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
                    w1.put(x + ":" + y, w1.get(x + ":" + y) + d);
                }
            }
        }
        return ints;
    }

    private static HashMap<String, Integer> drawWire(String in){
        HashMap<String, Integer> wire = new HashMap<String, Integer>();
        int x = 0, y = 0, d = 0;
        for(String s : in.split(",")){
            char dir = s.charAt(0);
            int len = Integer.parseInt(s.substring(1));
            int[] delta = getDir(dir);
            for(int i = 0; i < len; i++){
                x += delta[0];
                y += delta[1];
                wire.put(x + ":" + y, ++d);
            }
        }
        return wire;
    }

    // Return int[] = {Δx, Δy}
    private static int[] getDir(char c){
        switch(c){
            case 'R': return new int[] {1, 0};
            case 'L': return new int[] {-1, 0};
            case 'U': return new int[] {0, -1};
            case 'D': return new int[] {0, 1};
            default: return null;
        }
    }

    private static String[] parseInput(String s){
        File file = new File(s);
        try {
            Scanner in = new Scanner(file);
            String[] out = new String[2];
            out[0] = in.nextLine();
            out[1] = in.nextLine();
            in.close();
            return out;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}