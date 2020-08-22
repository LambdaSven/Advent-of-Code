package Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class D3P1 {
    public static void main(String[] args){
        String[] wires = parseInput(args[0]);
        String w1 = wires[0];
        String w2 = wires[1];
        HashMap<String, Integer> wire = drawWire(w1); 

        ArrayList<String> intersections = getIntersections(wire, w2);

        int closestInt = getClosest(intersections);

        System.out.printf("The Closest Intersection is %d units away\n", closestInt);

    }

    private static int getClosest(ArrayList<String> intersections){
        int closest = Integer.MAX_VALUE;
        for(String s : intersections){
            String[] temp = s.split(":");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            if(Math.abs(x) + Math.abs(y) < closest){closest = Math.abs(x) + Math.abs(y);}
        }
        return closest;
    }

    private static ArrayList<String> getIntersections(HashMap<String, Integer> w1, String w2){
        int x = 0; int y = 0;
        ArrayList<String> ints = new ArrayList<String>();
        for(String s : w2.split(",")){
            char dir = s.charAt(0);
            int len = Integer.parseInt(s.substring(1));
            int[] delta = getDir(dir);
            for(int i = 0; i < len; i++){
                x += delta[0];
                y += delta[1];
                if(w1.containsKey(x + ":" + y)) {
                    ints.add(x + ":" + y);
                }
            }
        }
        return ints;
    }

    private static HashMap<String, Integer> drawWire(String in){
        HashMap<String, Integer> wire = new HashMap<String, Integer>();
        int x = 0, y = 0;
        for(String s : in.split(",")){
            char dir = s.charAt(0);
            int len = Integer.parseInt(s.substring(1));
            int[] delta = getDir(dir);
            for(int i = 0; i < len; i++){
                x += delta[0];
                y += delta[1];
                wire.put(x + ":" + y, 1);
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