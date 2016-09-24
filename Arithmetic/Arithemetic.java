/* Author - Benjaman Dutton
 * 26/7/16
 * Arithmetic game program. Finds a if a set of numbers can be used to get to a goal number.
 * Checks every possible combination until it finds something that works or exhausts combinations.
 * Expects valid input at all times!
 * takes a while when around 30 or more numbers are input
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Arithemetic {
  
  public static void main(String [] args) throws IOException{
    
    while(true){
      //read stuff
      BufferedReader numLine = new BufferedReader(new InputStreamReader(System.in));
      ArrayList<Integer> n = new ArrayList<Integer>(); //n for the numbers
      for (String a : numLine.readLine().split(" ")) n.add(Integer.valueOf(a)); 
      String [] rules = numLine.readLine().split(" "); // stores the rules
      int t = Integer.valueOf(rules[0]); // t for target
      boolean propop = (rules[1].equals("N")) ? true : false; // keeps track of proper operations
      System.out.print((propop ? "N " : "L " ));
      //calls method to create the formula
      boolean possible = calculate(t,n,propop);
      if (!possible) System.out.println("Impossible");
    }
  }
  
  /* Do calculations - either left to right or proper order
   * @param c the matrix of all possible operator Combinations
   * @param t the Target value
   * @param n the Numbers that need to reach the target
   * @param r if the rule is proper order or not
   * @return the formula, it will be empty if impossible
     */
  private static boolean calculate(int t,ArrayList<Integer> n, boolean r){
    String formula = "";
    if (!r){ // if left to right is specified
      for (int i = 0; i < Math.pow(2,n.size() -1); i ++){
        int total = n.get(0);
        String c = Integer.toBinaryString(i);
        String s = "";
        for (int q = 0; q < n.size() -1; q++){
          s += 0;
        }
        c = s.substring(0,s.length() - c.length()) + c;
        for (int j = 0; j < c.length(); j ++){
          total = c.substring(j,j+1).equals("0") ? total * n.get(j+1) : total + n.get(j+1);
        }
        if (total == t) return makeString(c,n);
      }
    }else{ //if proper order is specified
      for (int i = 0; i < Math.pow(2,n.size() -1); i++){
        String c = Integer.toBinaryString(i);
        String s = "";
        for (int q = 0; q < n.size() -1; q++){
          s += 0;
        }
        c = s.substring(0,s.length() - c.length()) + c;
        int total = 0;
        ArrayList<Integer> copy = new ArrayList<Integer>();
        for (int a : n) copy.add(a);
        for (int j = c.length() - 1; j >= 0; j --){
          if (c.charAt(j) == '0'){
            copy.set(j,copy.get(j) * copy.get(j+1)) ;
            copy.remove(j+1);
          }
        }
        for (int x : copy) total += x;
        if (total == t) return makeString(c,n);
      }
    }
    return false;
  }
  
  /* Makes a formula given a successful array of operands
   * @param c the array of operands
   * @param n the numbers from the user
   * @return string to represent the successful formula
   */
  private static boolean makeString(String c, ArrayList<Integer> n){
    System.out.print(n.get(0));
    for (int j = 0; j < c.length(); j++) {
      System.out.print(c.substring(j,j+1).equals("0") ? (" * " + n.get(j+1) ) : " + " + n.get(j+1));
    }
    System.out.println();
    return true;
  }
  
 }
    