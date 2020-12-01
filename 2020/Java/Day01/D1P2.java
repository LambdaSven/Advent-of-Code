package Day01;

import java.io.File;
import java.util.ArrayList;

import aocutil.Utilities;

public class D1P2 {
  public static void main(String[] args){
    final int TARGET = 2020;
    int product = 0;
    
    ArrayList<Integer> data = Utilities.parseToInts(new File(args[0]));

    //O(n²)
    for(int i = 0; i < data.size(); i++){
      for(int j = i+1; j < data.size(); j++){
        if(data.contains(TARGET - data.get(i) - data.get(j))){
          //match!!
          product = data.get(i) * data.get(j) * (TARGET - data.get(i) - data.get(j));
          break;
        }
      }
    }

    System.out.printf("Your Expense Report Error Code is %d\n", product);
  }
}
