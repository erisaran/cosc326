/** Author Benjaman Dutton - ID # 247060
  * 
 **/

import java.util.*;

public class Anagrams {
  
  // stores the dictionary based on word length
  private static Hashtable<Integer,ArrayList<String>> h = new Hashtable<Integer,ArrayList<String>>();
  // max amount of words per anagram
  private static int max = 0;
  // stores the place to avoid double ups when searching for same length words in the same anagram
  private static ArrayList<Integer> countPlace = new ArrayList<Integer>();
  // the sentence represented in alphabetical order
  private static String sortedSentence = "";
  // stores the anagrams in order of how they should be printed out
  private static ArrayList<ArrayList<String>> anagrams = new ArrayList<ArrayList<String>>();
  
  public static void main(String [] args){
    
    Scanner d = new Scanner(System.in);
    String sentence = ""; int sl = 0;
    
    // process args
    if (args.length == 2){
      sentence = args[0].toLowerCase().replaceAll("[^a-z]*", "");
      sl = sentence.length();
      sortedSentence = sortWord(sentence);
      max = Integer.valueOf(args[1]);
    }else System.out.println("Wrong amount of args");
    
    for (int i = 1; i <= sentence.length(); i++) h.put(i, new ArrayList<String>());
    
    // read stuff and does some processing so that only words that could potentially be in
    // anagrams are added to the hash table
    while(d.hasNextLine()){
      String word = d.nextLine().toLowerCase();
      boolean pw = true; // possible word boolean flag
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
    
    for (int i = 0; i < max; i ++) anagrams.add(new ArrayList<String>());
    
    // do the calculations
    searchForAnagrams(sl,sl,"");
    
    //prints anagrams
    for (int i = 0; i < anagrams.size(); i++){
      for (int k = 0; k < anagrams.get(i).size(); k ++){
        System.out.println(anagrams.get(i).get(k));
      }
    }
  }
  
  // recursively create a string with the same number of characters as the starting sentence
  private static void addWords(Integer [] comb, int position, String sen){
    ArrayList<String> work = h.get(comb[position]); // an array of words of a certain length
    for (int i = countPlace.get(comb[position]-1); i < work.size(); i++){
      countPlace.set(comb[position] -1, i + 1);
      for (int a = 0; a < comb[position] - 1; a++) countPlace.set(a, 0);
      if (comb.length == position + 1){
        if (position == 0) {
          String w = work.get(i);
          if (sortWord(w).equals(sortedSentence)) anagrams.get(comb.length - 1).add(w);
        }
        else {
          String w = sen + " " + work.get(i);
          if (sortWord(w).equals(sortedSentence)) anagrams.get(comb.length - 1).add(w);
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
  public static void searchForAnagrams(int n, int m, String prefix) {
    if (n == 0) {
      countPlace.clear();
      for (int z = 0; z < sortedSentence.length(); z++) countPlace.add(0);
      String [] comb = prefix.split(" ");
      Integer [] icom = new Integer [comb.length -1];
      for (int q = 1; q < comb.length; q ++){
        icom[q-1] = Integer.valueOf(comb[q]);
      }
      if (icom.length <= max) addWords(icom, 0, "");
      return;
    }
    for (int i = Math.min(m, n); i >= 1; i--) {
      searchForAnagrams(n-i, i, prefix + " " + i);
    }
  }
}