/** Authors Ben Dutton & Francesco Lee
  * we generated a pascal triangle to find the answer without needed to manipulate any 
  * number bigger than long we modeled part of our answer off of some code 
  * found at http://stackoverflow.com/questions/2201113/combinatoric-n-choose-r-in-java-math
  */

public class Counting {
  public static void main (String[] args) {
    long n = 0;
    long k = 0;
    if (args.length == 2){
      n = Integer.valueOf(args[0]);
      k = Integer.valueOf(args[1]);
    }else {
      System.out.println("Error incorrect number of args");
    }
    long nCk = 1;
    for (long kc = 0; kc <= n; kc++) {
      if (k == kc) System.out.println(nCk);
      nCk = (nCk / (kc+1)) * (n-kc);
    }
  }
}
