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

    private static int totalOrbits(HashMap<String, Planet> map){
        int count = 0;
        for(Planet p : map.values()){
            count += p.distanceTo("COM");
        }
        return count;
    }

    private static HashMap<String, Planet> createMap(ArrayList<String> r){
        HashMap<String, Planet> map = new HashMap<String, Planet>();

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
            if(!root.equals("COM")){
                map.get(child).setParent(map.get(root));
            } else {
                map.get(child).setParent(new Planet("COM"));
            }
        }
        return map;
    }

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
