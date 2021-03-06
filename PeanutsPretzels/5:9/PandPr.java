
/**
 * This is a program used to determine the moves that the user must make in
 * order to always win the game proposed in the Peanuts and Pretzels etude.
 * 
 * For COSC 326, S2 2016
 * 
 * @author Thomas Crisp, Ben Dutton, Murdoch Braid and Francesco Lee
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.util.Scanner;

public class PandPr {
  
  private static ArrayList<Integer> pean;
  private static ArrayList<Integer> pret;
  private static ArrayList<Character> sign1;
  private static ArrayList<Character> sign2;
  private static int peanuts;
  private static int pretzels;
  private static byte[][] state;
  private static boolean[][] rules;
  
  
  public static void main(String[] args) throws IOException {
    // read stuff
    //BufferedReader inputs = new BufferedReader(new InputStreamReader(System.in));
    Scanner inputs = new Scanner(System.in);
    
    String outputs = "";
    while (inputs.hasNextLine()) {
      boolean found = false;
      pean = new ArrayList<Integer>();
      pret = new ArrayList<Integer>();
      sign1 = new ArrayList<Character>();
      sign2 = new ArrayList<Character>();
      String [] amount = inputs.nextLine().split(" ");
      if (amount.length != 2) break;
      peanuts = Integer.valueOf(amount[0]);
      pretzels = Integer.valueOf(amount[1]);
      String[] s;
      
      while (inputs.hasNextLine()) {
        s = inputs.nextLine().split(" ");
        if (s.length != 2) break;
        sign1.add(s[0].charAt(0));
        pean.add(Integer.valueOf(s[0].substring(1, s[0].length())));
        sign2.add(s[1].charAt(0));
        pret.add(Integer.valueOf(s[1].substring(1, s[1].length())));
      }
      
      
      rules = new boolean[peanuts + 1][pretzels + 1];
      state = new byte[peanuts + 1][pretzels + 1];
      for (boolean[] row : rules) {
        for (boolean b : row) {
          b = false;
        }
      }
      for (byte[] row : state) {
        for (byte b : row) {
          b = 0x00;
        }
      }
      mm();
      
      pean.add(0);
      pret.add(1);
      sign1.add('=');
      sign2.add('='); // base move 1
      pean.add(1);
      pret.add(0);
      sign1.add('=');
      sign2.add('='); // base move 2
      
      /*for (int i = 0; i < pean.size(); i++) {
        System.out.println(pean.get(i) + " " + pret.get(i) + "\n");
      }
      System.out.println("kahfkjhjwrnrjnrjkfnrjnfewnfewnfejkrw");
      */
      for (int i = 0; i < pean.size(); i++) {
        if (peanuts - pean.get(i) == 0 && pretzels - pret.get(i) == 0) {
          outputs += pean.get(i) + " " + pret.get(i) + "\n";
          found = true;
          break;
        }
      }
      if (!found) {
        for (int i = 0; i < pean.size(); i++) {
          int a = peanuts - pean.get(i);
          int b = pretzels - pret.get(i);
          if (a >= 0 && b >= 0) {
            if (t(peanuts - pean.get(i), pretzels - pret.get(i),
                  1)) {
              outputs += pean.get(i) + " " + pret.get(i) + "\n";
              found = true;
              break;
            }
          }
        }
      }
      // if no solution is found, return 0 0 to indicate that the given
      // situation is impossible to win
      if (!found) {
        outputs += "0 0\n";
      }
      
      }
      System.out.print(outputs);
    }
  
  /**
   * 
   * @param a
   * @param b
   * @param gen
   * @return true if the move is good
   * @return false if the move is not good
   */
  private static boolean y(int a, int b, int gen) {
    if (a == 0 && b == 0)
      return false;
    if (state[a][b] == 0x01) {
      return true;
    } else if (state[a][b] == 0x02) {
      return false;
    }
    // System.out.println(gen + " you " + a + " " + b);
    for (int i = 0; i < pean.size(); i++) {
      if (a - pean.get(i) == 0 && b - pret.get(i) == 0) {
        state[a][b] = 0x01;
        // System.out.println(pean.get(i) + " " + pret.get(i));
        return true;
      }
    }
    for (int i = 0; i < pean.size(); i++) {
      int p1 = a - pean.get(i);
      int p2 = b - pret.get(i);
      if (p1 >= 0 && p2 >= 0) {
        if (t(p1, p2, gen + 1)) {
          // System.out.println(pean.get(i) + " " + pret.get(i));
          //state[a][b] = 0x01;
          return true;
        }
      }
    }
    state[a][b] = 0x02;
    return false;
    
  }
  
  /**
   * @param a
   * @param b
   * @param gen
   * @return
   */
  private static boolean t(int a, int b, int gen) {
    if (a == 0 && b == 0)
      return true;
    if (state[a][b] == 0x01) {
      return false;
    } else if (state[a][b] == 0x02) {
      return true;
    }
    // System.out.println(gen + " them " + a + " " + b + " ");
    for (int i = 0; i < pean.size(); i++) {
      if (a - pean.get(i) == 0 && b - pret.get(i) == 0) {
        // System.out.println(pean.get(i) + " " + pret.get(i));
        state[a][b] = 0x01;
        return false;
      }
    }
    for (int i = 0; i < pean.size(); i++) {
      int p1 = a - pean.get(i);
      int p2 = b - pret.get(i);
      if (p1 >= 0 && p2 >= 0) {
        // System.out.println("their turn " + pean.get(i) + " " +
        // pret.get(i));
        if (y(p1, p2, gen + 1) == false) {
          state[a][b] = 0x01;
          return false;
        }
      }
    }
    state[a][b] = 0x02;
    return true;
  }
  
  /**
   * This method manages the data structures in which we use to keep track of
   * the peanuts and pretzels
   * 
   */
  private static void mm() {
    int count = 0;
    for (int i = 0; i < sign1.size(); i++) {
      if (sign1.get(i) == '<' && sign2.get(i) == '<') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          for (int k = 0; k < p2; k++) {
            if (rules[j][k] == false) {
              pean.add(j);
              pret.add(k);
              rules[j][k] = true;
            }
          }
        }
      } else if (sign1.get(i) == '>' && sign2.get(i) == '<') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        i--;
        for (int j = p1 + 1; j <= peanuts; j++) {
          for (int k = 0; k < p2; k++) {
            if (rules[j][k] == false) {
              pean.add(j);
              pret.add(k);
              rules[j][k] = true;
            }
          }
        }
      } else if (sign1.get(i) == '>' && sign2.get(i) == '>') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        count++;
        for (int j = p1 + 1; j <= peanuts; j++) {
          for (int k = p2 + 1; k <= pretzels; k++) {
            if (rules[j][k] == false) {
              pean.add(j);
              pret.add(k);
              rules[j][k] = true;
            }
          }
        }
      } else if (sign1.get(i) == '<' && sign2.get(i) == '>') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          for (int k = p2 + 1; k <= pretzels; k++) {
            if (rules[j][k] == false) {
              pean.add(j);
              pret.add(k);
              rules[j][k] = true;
            }
          }
        }
      } else if (sign1.get(i) == '=' && sign2.get(i) == '<') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        i--;
        for (int j = 0; j < p2; j++) {
          if (rules[p1][j] == false) {
            pean.add(p1);
            pret.add(j);
            rules[p1][j] = true;
          }
        }
      } else if (sign1.get(i) == '<' && sign2.get(i) == '=') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        i--;
        for (int j = 0; j < p1; j++) {
          if (rules[j][p2] == false) {
            pean.add(j);
            pret.add(p2);
            rules[j][p2] = true;
          }
        }
      } else if (sign1.get(i) == '>' && sign2.get(i) == '=') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        i--;
        for (int j = p1 + 1; j <= peanuts; j++) {
          if (rules[j][p2] == false) {
            pean.add(j);
            pret.add(p2);
            rules[j][p2] = true;
          }
        }
      } else if (sign1.get(i) == '=' && sign2.get(i) == '>') {
        int p1 = pean.get(i);
        int p2 = pret.get(i);
        pean.remove(i);
        pret.remove(i);
        sign1.remove(i);
        sign2.remove(i);
        i--;
        for (int j = p2 + 1; j <= pretzels; j++) {
          if (rules[p1][j] == false) {
            pean.add(p1);
            pret.add(j);
            rules[p1][j] = true;
          }
        }
      }
    }
  }
}