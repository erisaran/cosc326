import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;
import java.util.ArrayList;

public class Ant {
  
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String dnaInput;
    int step = 10; // number of steps the ant takes
    ArrayList<String> dnaList = new ArrayList<String>();
    while (true){
      while ((dnaInput = in.readLine()) != null) {
        if (dnaInput.equals("")) {
          //terminates if a completely empty line is sent to standard input
          break;
        } else if ((dnaInput.trim().startsWith("#"))) {
          // don't put input into the array list if it starts with #, as per the
          // specs
        } else {
          try {
            if (Integer.valueOf(dnaInput) >= 0){
              step = Integer.valueOf(dnaInput);
            }
          }catch (NumberFormatException e){
            dnaList.add(dnaInput);
          }
          //System.out.println(dnaInput); //this is just a debug line
        }
      }
      
      //String dna1 = "w ESWN bbbb";
      //String dna2 = "b WNES wwww";
      // takes the colour of the first line of DNA and makes it the default
      String tileDefault = dnaList.get(0).substring(0, 1);
      
      String lastMove = "N";
      
      AntCell location = new AntCell(tileDefault); // creates a location object
      // signifying the start point
      
      for (int i = 1; i <= step; i++) { // repeats up to the number of steps
        String dnaLine = "";
        
        //System.out.println(location.getC());
        for (int j = 0; j < dnaList.size(); j++){
          if (dnaList.get(j).substring(0,1).equals(location.getC())){
            dnaLine = dnaList.get(j);
            break;
          }
        }
        
        String col = dnaLine.substring(0,1);
        String moves = dnaLine.substring(2,6);
        String set = dnaLine.substring(7,11);
        
        int dp = moves.indexOf(lastMove); //records position on the dna line
        
        char [] a = {moves.charAt((dp +1)%4)};
        String nextMove = new String(a);
        location = new AntCell(tileDefault, move(nextMove), location);
        
        char [] b = {set.charAt((dp+1)%4)};
        location.getP().setC(new String(b));
        
        lastMove = nextMove;
        
        // this part checks to see if it has visited its current location
        // before
        AntCell lastCell = location.getP();
        //System.out.println(location.getP().getC());
        
        while (lastCell != null) {
          if (lastCell.getX() == location.getX() && lastCell.getY() == location.getY()) {
            location.setC(lastCell.getC()); // if it has then change the
            // colour from default to
            // what it was
            break;
          }
          //System.out.println(lastCell.getC());
          lastCell = lastCell.getP();
          
        }
        //System.out.println(location.getP());
        System.out.println("Step " + i + "   " + location.getX() + ", " + location.getY() + " " + location.getC());
      }
      
      System.out.println("Ant ends at cell " + location.getX() + ", " + location.getY());
    }
  }
  
  private static int move(String s) {
    if (s.equals("E")) {
      return 1;
    } else if (s.equals("W")) {
      return -1;
    } else if (s.equals("N")) {
      return 2;
    } else {
      return -2;
    }
  }
}
