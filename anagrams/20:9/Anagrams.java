/** Author Benjaman Dutton - ID # 247060
 **/

import java.util.*;

public class Anagrams {
  
  private static Hashtable<Integer,ArrayList<String>> h = new 
    Hashtable<Integer,ArrayList<String>>();
  private static ArrayList<ArrayList<Integer []>> c = new ArrayList<ArrayList<Integer []>>();
  private static ArrayList<String> s = new ArrayList<String>();
  private static ArrayList<String> possibleAnagrams = new ArrayList<String>();
  private static int max = 0;
  private static ArrayList<Integer> countPlace = new ArrayList<Integer>();
  
  public static void main(String [] args){
    
    Scanner d = new Scanner(System.in);
    String sentence = ""; int sl = 0; String sortedSentence = "";
    
    // process args
    if (args.length == 2){
      sentence = args[0].toLowerCase().replaceAll("[^a-z]*", "");
      sl = sentence.length();
      sortedSentence = sortWord(sentence);
      max = Integer.valueOf(args[1]);
    }else System.out.println("Not enough args");
    for (int i = 1; i <= sentence.length(); i++) h.put(i, new ArrayList<String>());
    
    // read stuff
    while(d.hasNextLine()){
      String word = d.nextLine().toLowerCase();
      boolean pw = true;
      for (int i = 0; i < word.length(); i++){
        if (sentence.indexOf(word.charAt(i)) == -1) pw = false;
      }
      Integer wl = word.length();
      if (pw){
        if (h.containsKey(wl)) h.get(wl).add(word);
        else {
          h.get(wl).add(word);
        }
      }
    }
    
    
    
    //make combnations
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
      if (icom.length <= max) c.get(icom.length -1).add(icom);
    }
    
    for (int i = 0; i < c.size(); i++){
      for (int j = 0; j < c.get(i).size(); j++){
        countPlace.clear();
        for (int z = 0; z < sentence.length(); z++) countPlace.add(0);
        addWords(c.get(i).get(j), 0, "");
        
      }
    }
          
    
    // check if they are anagrams
    //ArrayList<String> list = h.get(sl);
    for (int i = 0; i < possibleAnagrams.size(); i++){
      String w = possibleAnagrams.get(i);
      if (sortWord(w).equals(sortedSentence)) System.out.println(w);
    }
    
    System.out.println(h.get(2));
    //System.out.println(possibleAnagrams);
    //System.out.println(sortedSentence + " " + h);
  }
  
  
  
  
  
  
  
  
  // recursively create a string with the same number of characters as the starting sentence
  private static void addWords(Integer [] comb, int position, String sen){
    ArrayList<String> work = h.get(comb[position]); // an array of words of a certain length
    
    for (int i = countPlace.get(comb[position]-1); i < work.size(); i++){
      countPlace.set(comb[position] -1, i + 1);
      if (comb.length == position + 1){
        if (position == 0) possibleAnagrams.add(work.get(i));
        else possibleAnagrams.add(sen + " " + work.get(i));
      }else {
        if (position == 0) addWords(comb, position + 1, work.get(i));
        else { addWords(comb, position + 1, sen + " " + work.get(i));
        }
      }
    }
  }
  
  private static String sortWord(String word){
    String sortedSentence = "";
    for (Character a = 'a'; a <= 'z'; a++){
      for (int i = 0; i < word.length() ; i++){
        if (word.charAt(i) == a) sortedSentence += a.toString();
      }
    }
    return sortedSentence;
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


/*
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
      */