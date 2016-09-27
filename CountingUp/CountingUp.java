import java.util.*;

public class CountingUp {
  
  private static int n;
  private static int k;
  private static ArrayList<ArrayList<Integer>> p = new ArrayList<ArrayList<Integer>>();
  
  public static void main(String[] args){
    if (args.length == 2){
      n = Integer.valueOf(args[0]);
      k = Integer.valueOf(args[1]);
    }else {
      System.out.println("Error incorrect number of args");
    }
    
    p.add(new ArrayList<Integer>());
    p.get(0).add(1);
    
    compute(1);
    
  }
  
  private static void compute(int row){
    p.add(new ArrayList<Integer>());
    for (int i = 0; i < p.get(row - 1).size(); i++){
    }
    
    
  }
  
}