/* 
 * Input must be single letters without spaces to represent colours
 * an empty line will signal to the program to output the result
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;
import java.util.ArrayList;

class MakeCarpet {
  
  public static void main(String [] args) throws IOException{
    
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<String> stock = new ArrayList<String>();
    String piece; int length = 0;
    while((piece = input.readLine()) != null){
      if (stock.size() == 0) length = piece.length();
      if (length == piece.length()) stock.add(piece);
      else if (piece.equals("")) break;
      else System.out.println("Error: Pieces of carpet must be the same length ");
    }
    
    int [] [] stockNumbered = new int [stock.size()][length];
    
    for (int i = 0; i < stock.size(); i++){
      for (int j = 0; j < length; j ++){
        stockNumbered[i][j] = Character.getNumericValue(stock.get(i).charAt(j));
        System.out.print(stockNumbered[i][j] + " ");
      }
      System.out.println();
    }
    
  }
}