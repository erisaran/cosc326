/**
 * Authors - Ben Dutton, Francesco Lee
 * Ants programs, etude 1
 * Keeps track of the scenarios.
 */

public class Scenario{
  
  private String [] d;
  private String l;
  
  public Scenario(String [] dna, String location){
    d = dna;
    l = location;
  }
  
  public void print(){
    for (String s : d){
      System.out.println(s);
    }
    System.out.println(l);
  }
  
}