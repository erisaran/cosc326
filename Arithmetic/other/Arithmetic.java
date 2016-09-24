import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Arithmetic{
  
  public static void main(String [] args){
    boolean leftToRight = false;
    while (true){
      int target = 0;
      Scanner input = new Scanner(System.in);
      List numbers = new ArrayList();
      while (input.hasNextInt()){
        numbers.add(input.nextInt());
      }
      input.nextLine();
      System.out.println("hoi");
      Scanner input2 = new Scanner(System.in);
      target = input2.nextInt();
      if (input.next().equals("N")){
        leftToRight = false;
      }else if (input.next().equals("L")){
        leftToRight = true;
      }
      
    }
    
  }
}