/** Author Benjaman Dutton - ID # 247060
  * problem with the counter when forming possible anagrams - 
 **/

import java.util.*;

public class Anagrams {
  
  private static Hashtable<Integer,ArrayList<String>> h = new Hashtable<Integer,ArrayList<String>>();
  private static int max = 0;
  private static ArrayList<Integer> countPlace = new ArrayList<Integer>();
  private static String sortedSentence = "";
  
  public static void main(String [] args){
    
    Scanner d = new Scanner(System.in);
    String sentence = ""; int sl = 0;
    
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
    // do the calculations
    searchForAnagrams(max,max,"");
  }
  
  // recursively create a string with the same number of characters as the starting sentence
  private static void addWords(Integer [] comb, int position, String sen){
    ArrayList<String> work = h.get(comb[position]); // an array of words of a certain length
    
    //// needs to reset count place for all the numbers lower than itself
    for (int i = countPlace.get(comb[position]-1); i < work.size(); i++){
      countPlace.set(comb[position] -1, i + 1);
      if (comb.length == position + 1){
        if (position == 0) {
          String w = work.get(i);
          if (sortWord(w).equals(sortedSentence)) System.out.println(w);
        }
        else {
          String w = sen + " " + work.get(i);
          if (sortWord(w).equals(sortedSentence)) System.out.println(w);
        }
      }else {
        if (position == 0) addWords(comb, position + 1, work.get(i));
        else { addWords(comb, position + 1, sen + " " + work.get(i));
        }
      }
    }
  }
  
  // sorts the word aplhabetically
  private static String sortWord(String word){
    String sortedSentence = "";
    for (Character a = 'a'; a <= 'z'; a++){
      for (int i = 0; i < word.length() ; i++){
        if (word.charAt(i) == a) sortedSentence += a.toString();
      }
    }
    return sortedSentence;
  }
  
  // finds all combinations of words that equal the intial sentence length
  public static void searchForAnagrams(int n, int max, String prefix) {
    if (n == 0) {
      countPlace.clear();
      for (int z = 0; z < sortedSentence.length(); z++) countPlace.add(0);
      String [] comb = prefix.split(" ");
      Integer [] icom = new Integer [comb.length -1];
      for (int q = 1; q < comb.length; q ++){
        icom[q-1] = Integer.valueOf(comb[q]);
      }
      addWords(icom, 0, "");
      return;
    }
    for (int i = Math.min(max, n); i >= 1; i--) {
      searchForAnagrams(n-i, i, prefix + " " + i);
    }
  }
}