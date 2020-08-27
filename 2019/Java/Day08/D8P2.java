package Day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class D8P2 {

  public static void main(String[] args){
    ArrayList<pixel> data = parseInput(args[0]);
    ArrayList<pixel> image = genImage(data, 25, 6);
    drawImage(image, 25, 6);
  }

  /**
   * We use an enum for clarity instead of using 0, 1, 2 all over the place.
   */
  public enum pixel {
    Black, White, Transparent
  }

  /**
   * Parses a pixel into a unicode character based on it's status
   * @param p - The pixel to return
   * @return String - a single unicode character representing the pixel
   */
  public static String drawPixel(pixel p){
    switch(p){
      case Black:
        return "■";
      case White:
        return "□";
      default:
        return null;
    }
  }

  /**
   * Renders the image to the console output, simply progresses through the List
   * inserting new lines as necessary
   * @param data - The image data
   * @param dimX - The size of the X dimension
   * @param dimY - The size of the Y dimension
   */
  public static void drawImage(ArrayList<pixel> data, int dimX, int dimY){
    for(int i = 0; i < dimX*dimY; i++){
      System.out.print(drawPixel(data.get(i)));
      if((i+1) % dimX == 0){
        System.out.println();
      }
    }
  }

  /**
   * This method will generate the final image, adding pixels to the output list
   * when needed.
   * @param data - The input data
   * @param dimX - The size of the X dimension
   * @param dimY - The size of the Y dimension
   * @return ArrayList<pixel> of size dimX * dimY
   */
  public static ArrayList<pixel> genImage(ArrayList<pixel> data, int dimX, int dimY){
    ArrayList<pixel> out = new ArrayList<pixel>();
    //Initilize the array
    for(int i = 0; i < dimX*dimY; i++){
      out.add(pixel.Transparent);
    }

    for(int i = 0; i < data.size(); i++){
      int cursor = i % (dimX * dimY);
      if(!out.get(cursor).equals(pixel.Transparent)){ // Pixel already drawn
        continue;
      } else {
        if(data.get(i).equals(pixel.Black)){
          out.set(cursor, pixel.Black);
        }
        if(data.get(i).equals(pixel.White)){
          out.set(cursor, pixel.White);
        }
      }
    }
    return out;
  }

  /**
   * We need to parse the input into a list of pixels for this problem;
   * We will start reading it as a string, and then parse each char into the appropriate pixel type
   * @param fp - The file Path
   * @return ArrayLisr<pixel> containing the data from the input file
   */
  private static ArrayList<pixel> parseInput(String fp){
    File file = new File(fp);
    ArrayList<pixel> data = new ArrayList<pixel>();
    try {
      Scanner sc = new Scanner(file);
      String temp = sc.nextLine(); //our input is one very long line
      for(int i = 0; i < temp.length(); i++){
        switch(temp.charAt(i) - '0'){
          case 0:
            data.add(pixel.Black);
            break;
          case 1:
            data.add(pixel.White);
            break;
          case 2:
            data.add(pixel.Transparent);
            break;
        }
      }
      sc.close();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
    return data;
  }
  
}