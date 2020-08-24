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

    private static int getTransfers(HashMap<String, Planet> map, String s, String d){
        ArrayList<String> sTree = map.get(s).getTree("COM");
        ArrayList<String> dTree = map.get(d).getTree("COM");

        //We need to traverse the list starting at COM
        Collections.reverse(sTree);
        Collections.reverse(dTree);
        String div = findDivergence(sTree, dTree);

        //We get the distances based on the parents, as we are already in orbit of them
        return (map.get(s).getParent().distanceTo(div) + map.get(d).getParent().distanceTo(div));
    }

    private static String findDivergence(ArrayList<String> s, ArrayList<String> d){
        String out = s.get(0);
        for(int i = 0; s.get(i) == d.get(i); i++){
            out = s.get(i);
        }
        return out;
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