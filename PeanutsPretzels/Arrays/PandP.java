/** 
 * Peanuts and Pretzels game app
 * Recursively makes turns to determine if any starting turn can guarantee a victory
 * Author Benjaman Dutton - and others
 * 
 * Will slow down if multiple rules with greater than or less than signs are used
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;
import java.util.ArrayList;

public class PandP {
  
  private static ArrayList<Integer> pean = new ArrayList<Integer>();
  private static ArrayList<Integer> pret = new ArrayList<Integer>();
  private static ArrayList<Character> sign1 = new ArrayList<Character>();
  private static ArrayList<Character> sign2 = new ArrayList<Character>();
  private static int peanuts;
  private static int pretzels;
  
  public static void main(String [] args) throws IOException{
    //read stuff
    BufferedReader inputs = new BufferedReader(new InputStreamReader(System.in));
    while (true){
      pean = new ArrayList<Integer>();
      pret = new ArrayList<Integer>();
      sign1 = new ArrayList<Character>();
      sign2 = new ArrayList<Character>();
      String [] amount = inputs.readLine().split(" ");
      peanuts = Integer.valueOf(amount[0]);
      pretzels = Integer.valueOf(amount[1]);
      String [] s;
      while ((s = inputs.readLine().split(" ")) != null) {
        if (s[0].equals("") || s[0].substring(0,1).equals(" ")) break;
        sign1.add(s[0].charAt(0));
        pean.add(Integer.valueOf(s[0].substring(1,s[0].length())));
        sign2.add(s[1].charAt(0));
        pret.add(Integer.valueOf(s[1].substring(1,s[1].length())));
      }
      pean.add(0);pret.add(1);sign1.add('=');sign2.add('='); //base move 1
      pean.add(1);pret.add(0);sign1.add('=');sign2.add('='); //base move 2
      //System.out.println(peanuts + " " + pretzels);
      mm(); // make moves with the > and < signs
      
      // check basic
      //if ((peanuts + pretzels)%2 == 1 && pean.size() == 2) {
      //  if (peanuts > 0) System.out.println("1 0");
      //  else System.out.println("0 1");
      //}else if ((peanuts + pretzels) % 2 == 0 && pean.size() ==2) System.out.println("0 0");
      if (!yt(peanuts, pretzels)) System.out.println("0 0");
      //System.out.println(pean.get(0) + " " + pret.get(0) + "\n" + pean.get(1) + " " + pret.get(1));
      for (int i = 0; i < pean.size(); i++){
        System.out.println(pean.get(i) + " " + pret.get(i) + "\n");
      }
    }
  }
  
  public static boolean yt(int pe, int pr){
    /* if this move is invalid return false
     */
    //if (pe <= 0 && pr <= 0) return false;
    System.out.println("Your turn " + pe + " " + pr);
    // Checks if it can win this turn
    
    /* if the game can be won this turn return true
      */
    for (int i = 0; i < pean.size(); i ++){
      if (pe - pean.get(i) == 0 && pr - pret.get(i) == 0){
        //if (pe == peanuts && pr == pretzels) 
        System.out.println(pean.get(i) + " " + pret.get(i));
        return true;
      }
    }
    
    
    /* if the game cant be won this turn try each turn and search from each new state
      */
    for (int i = 0; i < pean.size(); i++){
      System.out.println(i);
      int p1 = pe - pean.get(i); int p2 = pr - pret.get(i);
      if (p1 < 0 || p2 < 0);
      else {
        if (tt(p1, p2)) {
          if (pe == peanuts && pr == pretzels) {
            System.out.println(pean.get(i) + " " + pret.get(i));
            return true;
          }
        }
      }
    }
    
    /* if none of the turns will guarantee a win then return false
     */
    return false;
  }
  
  public static boolean tt(int pe, int pr){
    /* if the turn is invalid return true
     */
    //if (pe <= 0 && pr <= 0) return true;
    //System.out.println("Their turn " + pe + " " + pr);
    
    // Checks if it can win this turn
    for (int i = 0; i < pean.size(); i ++){
      if (pe - pean.get(i) == 0 && pr - pret.get(i) == 0){
        return false;
      }
    }
    
    /* tries each of their turns, if any of their turns can result in a non-guarantee return false
     */
    for (int i = 0; i < pean.size(); i ++){
      //System.out.println(i);
      int p1 = pe - pean.get(i); int p2 = pr - pret.get(i);
      if (p1 >= 0 && p2 >= 0){
          if ((!yt(p1,p2)))
          {
            return false;
        }
      }
    }
    /* if none of their turns can win, and if none can block you then return true
     */
    return true;
  }
  
  private static void mm(){
    int count = 0;
    for (int a = 0; a < sign1.size(); a++){
      int i = a - count;
      if (sign1.get(i) == '<' && sign2.get(i) == '<'){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = 0; j < p1; j ++){
          for (int k = 0; k < p2; k ++){
            pean.add(j);
            pret.add(k);
          }
        }
      }else if (sign1.get(i) == '>' && sign2.get(i) == '<'){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = p1 + 1; j <= peanuts; j ++){
          for (int k = 0; k < p2; k ++){
            pean.add(j);
            pret.add(k);
          }
        }
      }else if (sign1.get(i) == '>' && sign2.get(i) == '>'){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = p1 + 1; j <= peanuts; j ++){
          for (int k = p2 + 1; k <= pretzels; k ++){
            pean.add(j);
            pret.add(k);
          }
        }
      }else if (sign1.get(i) == '<' && sign2.get(i) == '>'){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = 0; j < p1; j ++){
          for (int k = p2 + 1; k <= pretzels; k ++){
            pean.add(j);
            pret.add(k);
          }
        }
      }else if (sign1.get(i) == '=' && sign2.get(i) == '<'){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = 0; j < p2; j ++){
          pean.add(p1);
          pret.add(j);
        }
      }else if (sign1.get(i) == '<' && sign2.get(i) == '='){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = 0; j < pean.get(i); j ++){
          pean.add(j);
          pret.add(p2);
        }
      }else if (sign1.get(i) == '>' && sign2.get(i) == '='){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = p1 + 1; j <= peanuts; j ++){
          pean.add(j);
          pret.add(p2);
        }
      }else if (sign1.get(i) == '=' && sign2.get(i) == '>'){
        int p1 = pean.get(i); int p2 = pret.get(i);
        pean.remove(i); pret.remove(i); sign1.remove(i); sign2.remove(i);
        count ++;
        for (int j = p2 + 1; j <= pretzels; j ++){
          pean.add(p1);
          pret.add(j);
        }
      }
    }
  }
}