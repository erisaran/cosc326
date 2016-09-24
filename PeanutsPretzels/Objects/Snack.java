public class Snack {
  
  private int pean;
  private int pret;
  private char sign1;
  private char sign2;
  
  public Snack(String snek){
    String [] s = snek.split(" "); set(s[0], 0); set(s[1], 1);
  }
  
  private void set(String s, int i){
    if (i == 0){ 
      sign1 = s.charAt(0);
      pean = Integer.valueOf(s.substring(1,s.length()));
    }else {
      sign2 = s.charAt(0);
      pret = Integer.valueOf(s.substring(1,s.length()));
    }
  }
  
  public String toString() {
    return (sign1 + "" + pean + " " + sign2 + pret);
  }
  
  public int getPret(){
    return pret;
  }
  
  public int getPean(){
    return pean;
  }
  
  public char getSign1(){
    return sign1;
  }
  
  public char getSign2(){
    return sign2;
  }
}