package Day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class D8P1 {
	public static void main(String[] args){
		ArrayList<Integer> data = parseInput(args[0]);

		System.out.printf("The verification number is %d\n", getVerification(data, 25, 6));
	}

	/**
	 * All we need to do is count the number of 0's, 1's, and 2's in blocks of size dimX * dimY
	 * And do each comparisons every time we finish a layer.
	 * @param data - The image data
	 * @param dimX - The X dimension of the Data
	 * @param dimY - The Y dimension of the Data
	 * @return int - the verification code
	 */
	private static int getVerification(ArrayList<Integer> data, int dimX, int dimY){
		int[] digits = new int[3];
		int min0 = Integer.MAX_VALUE;
		int out = 0;
		for(int i = 0; i < data.size(); i++){
			digits[data.get(i)]++;
			//If the next i will be the start of the next layer, start comparisons
			if((i+1) % (dimX*dimY) == 0){
				if(digits[0] < min0){
					min0 = digits[0];
					out = digits[1] * digits[2];
				}
				digits = new int[3]; //Reset our array
			}
		}
		return out;
	}

	/**
	 * We need to parse the input into a list of Integers for this problem;
	 * But due to the length of the input we will start with reading it as a string,
	 * then parsing the string into the Integers
	 * @param fp - The file Path
	 * @return ArrayLisr<Integer> containing the data from the input file
	 */
	private static ArrayList<Integer> parseInput(String fp){
		File file = new File(fp);
		ArrayList<Integer> data = new ArrayList<Integer>();
		try {
			Scanner sc = new Scanner(file);
			String temp = sc.nextLine(); //our input is one very long line
			for(int i = 0; i < temp.length(); i++){
				data.add(temp.charAt(i) - '0');// ascii hack
			}
			sc.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return data;
	}
}