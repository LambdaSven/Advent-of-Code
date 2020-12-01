package aocutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Utilities {

  public static ArrayList<Integer> parseToInts(File fd){
    return listToInts(parseInputFile(fd));
  }

  //parse an input file into an arraylist of lines
  public static ArrayList<String> parseInputFile (File fd){
    ArrayList<String> out = new ArrayList<String>();
    try{
      Scanner sc = new Scanner(fd);
      while(sc.hasNextLine()){
        out.add(sc.nextLine());
      }
      sc.close();
    } catch(FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }
    return out;
  }

  public static ArrayList<Integer> listToInts(ArrayList<String> lines){
    ArrayList<Integer> out = null;
    try{
      out = lines.stream()
            .map(e -> Integer.parseInt(e))
            .collect(Collectors.toCollection(ArrayList::new));
    } catch (NumberFormatException e){
      e.printStackTrace();
      System.exit(1);
    }
    return out;
  }
}
