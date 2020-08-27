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

  /**
   * Ths function looks through our list of intersection points,
   * calculating the Manhattan Distance to (0, 0).
   * @param intersections - ArrayList<String> of intersection points
   * @return int - The Smallest Manhattan Distance between (0, 0) and all (x, y) in our list
   */
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

  /**
   * This function searching through the first wire, compares each point with
   * a point on the second wire, adding the point to a list of intersections.
   * 
   * @param w1 HashMap<String, Integer> - Our first Wire
   * @param w2 String - Our second wire in String representation
   * @return ArrayList<String> - List of all intersections between the two Wires.
   */
  private static ArrayList<String> getIntersections(HashMap<String, Integer> w1, String w2){

    int x = 0; int y = 0;
    ArrayList<String> ints = new ArrayList<String>();
    for(String s : w2.split(",")){
      // Logic here is very similar to drawWire
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

  /**
   * This function adds all points on a line to a HashMap, for easy lookup
   * 
   * @param in String - Our first Wire
   * @return HashMap<String, Integer> - The mapping of the input Wire
   */
  private static HashMap<String, Integer> drawWire(String in){

    HashMap<String, Integer> wire = new HashMap<String, Integer>();
    int x = 0, y = 0;
    //For each instruction in our input list
    for(String s : in.split(",")){
      //get the direction char
      char dir = s.charAt(0);
      //get the length
      int len = Integer.parseInt(s.substring(1));
      //parse the direction into {Δx, Δy}
      int[] delta = getDir(dir);
      //increase x and y by their respective Δx, Δy for the duration of the wire segment
      for(int i = 0; i < len; i++){
        x += delta[0];
        y += delta[1];
        //add all points to the wire, setting the value as 1 to indicate that it has been seen
        wire.put(x + ":" + y, 1);
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