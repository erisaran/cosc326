import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;
import java.util.ArrayList;

public class PandP {
  
  public static void main(String [] args) throws IOException{
    //read stuff
    BufferedReader numLine = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Snack> snack = new ArrayList<Snack>(); //n for the numbers
    String [] amount = numLine.readLine().split(" ");
    int peanuts = Integer.valueOf(amount[0]);
    int pretzels = Integer.valueOf(amount[1]);
    String s;
    while ((s = numLine.readLine()) != null) {
      if (s.equals("") || s.substring(0,1).equals(" ")) break;
      snack.add(new Snack(s));
    }
    //for (Snack a : snack) System.out.println(a);
    
    
  }
}