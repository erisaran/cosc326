
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
      boolean found = false;
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
      
      
      
      mm();
      
      pean.add(0);pret.add(1);sign1.add('=');sign2.add('='); //base move 1
      pean.add(1);pret.add(0);sign1.add('=');sign2.add('='); //base move 2
      
      /*for (int i = 0; i < pean.size(); i++){
       System.out.println(pean.get(i) + " " + pret.get(i) + "\n");
       }*/
      
      for (int i = 0; i < pean.size(); i++){
        if (peanuts - pean.get(i) == 0 && pretzels - pret.get(i) == 0){
          System.out.println("first " + pean.get(i) + " " + pret.get(i));
          found = true;
          break;
        }
      }
      if (!found){
        for (int i = 0; i < pean.size(); i++){
          if (t(peanuts - pean.get(i), pretzels - pret.get(i), 1)){
            System.out.println(pean.get(i) + " " + pret.get(i));
            found = true;
            break;
          }
        }
      }
      if (!found){
        System.out.println("0 0");
      }
    }
  }
  
  private static boolean y(int a, int b, int gen){
    if (a == 0 && b == 0) return false;
    //System.out.println(gen + " you " + a + " " + b);
    for (int i = 0; i < pean.size(); i ++){
      if (a - pean.get(i) == 0 && b - pret.get(i) == 0){
        //System.out.println(pean.get(i) + " " + pret.get(i));
        return true;
      }
    }
    for (int i = 0; i < pean.size(); i++){
      int p1 = a - pean.get(i); int p2 = b - pret.get(i);
      if (p1 >= 0 && p2 >= 0){
        if (t(p1,p2, gen + 1)){
          //System.out.println(pean.get(i) + " " + pret.get(i));
          return true;
        }
      }
    }
    return false;
    
  }
  
  private static boolean t(int a, int b, int gen){
    if (a == 0 && b == 0) return true;
    //System.out.println(gen + " them " + a + " " + b + " ");
    for (int i = 0; i < pean.size(); i ++){
      if (a - pean.get(i) == 0 && b - pret.get(i) == 0){
        //System.out.println(pean.get(i) + " " + pret.get(i));
        return false;
      }
    }
    for (int i = 0; i < pean.size(); i ++){
      int p1 = a - pean.get(i); int p2 = b - pret.get(i);
      if (p1 >= 0 && p2 >= 0){
        //System.out.println("their turn " + pean.get(i) + " " + pret.get(i));
        if (!y(p1,p2, gen + 1)){
          return false;
        }
      }
    }
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