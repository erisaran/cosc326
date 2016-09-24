import java.util.*;

public class Anagrams {
  
  
  private static Hashtable<String,String> h = new Hashtable<String,String>();
  
  public static void main(String [] args){
    
    Scanner d = new Scanner(System.in);
    String sentence = ""; int max = 0;
    
    if (args.length == 2){
      sentence = args[0].toLowerCase().replaceAll("[^a-z]*", "");
      max = Integer.valueOf(args[1]);
    }else System.out.println("Not enough args");
    
    while(d.hasNextLine()){
      String word = d.nextLine();
      h.put(word,word);
    }
    
    
    
    System.out.println(sentence + " " + max + "\n" + h);
  }
  
}
    