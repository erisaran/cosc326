import java.util.*;

public class sort {
  
  public static void main(String [] args){
    String sentence = "zxaqac";
    
    String word = "zxaqab";
    String word2 = "zxaqac";
    String w = "";
    String w2 = "";

    for (Character a = 'a'; a <= 'z'; a++){
      for (int i = 0; i < word.length() ; i++){
        if (word.charAt(i) == a) {
          if (sentence.indexOf(a) == -1){
            a = 'z';
            w = "";
          }else w += a.toString();
        }
      }
    }
    for (Character a = 'a'; a <= 'z'; a++){
      for (int i = 0; i < word2.length() ; i++){
        if (word2.charAt(i) == a) {
          if (sentence.indexOf(a) == -1){
            a = 'z';
            w2 = "";
          }else w2 += a.toString();
        }
      }
    }
    System.out.println(w + " " + w2);

  }
}