import java.util.*;

public class generate {
  
  public static ArrayList<String> s = new ArrayList<String>();
  
  
  public static void main(String []args){
    
    ArrayList<ArrayList<Integer []>> c = new ArrayList<ArrayList<Integer []>>();
    
    
    int sl = 7;
    int max = 3;
    
    partition(sl);
    
    for (int i = 0; i < sl; i++){
      c.add(new ArrayList<Integer[]>());
    }
    ArrayList<String> st = new ArrayList<String>();
    
    for (int i = 0; i < s.size(); i++) {
      String [] comb = s.get(i).split(" ");
      Integer [] icom = new Integer [comb.length -1];
      for (int q = 1; q < comb.length; q ++){
        icom[q-1] = Integer.valueOf(comb[q]);
      }
      c.get(icom.length -1).add(icom);
    }
    
    System.out.println(c);
  }
  
  public static void partition(int n) {
    partition(n, n, "");
  }
  public static void partition(int n, int max, String prefix) {
    if (n == 0) {
      s.add(prefix);
      return;
    }
    
    for (int i = Math.min(max, n); i >= 1; i--) {
      partition(n-i, i, prefix + " " + i);
    }
  }
}
