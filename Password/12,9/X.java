import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class X {
  
  public static void main(String [] args) {
    
    String [] s = {"a9 1 a-a9"};
    String [] sf = {" a", "a1a1-", "a1-a1 -a"};
    Pattern r = Pattern.compile("[a-zA-Z0-9]([a-zA-Z0-9]+[_ -.]?)*[a-zA-Z0-9]");
    for (int i = 0; i < s.length; i++){
      Matcher ra = r.matcher(s[i]);
      System.out.println(ra.matches());
    }
    for (int i = 0; i < sf.length; i++){
      Matcher ra = r.matcher(sf[i]);
      System.out.println(ra.matches());
    }
  }
}