import java.lang.Math;

public strictfp class Numbers{
  
  static int x1 = 2;
  static int x2 = 2000;
  static int x3 = 2000000;
  static int x4 = 2000000000;
  
  public static void main(String [] args){
    
    /*question_1();
    question_2();
    question_3();
    question_4();
    question_5();
    question_7();*/
    question_8();
  }
  
  private static void question_1(){
    
    System.out.println("Q1");
    System.out.println(midpoint(x1,x1) == x1);
    System.out.println(midpoint(x2,x2) == x2);
    System.out.println(midpoint(x3,x3) == x3);
    System.out.println(midpoint(x4,x4) == x4);
  }
  
  
  
  private static void question_2(){
    System.out.println("\nQ2");
    System.out.println(midpt2(x1,x1) == x1);
    System.out.println(midpt2(x2,x2) == x2);
    System.out.println(midpt2(x3,x3) == x3);
    System.out.println((midpt2(x4,x4) == x4 )+ "\n");
    
    System.out.println(midpoint(x1,x2) == midpoint(x2,x1));
    System.out.println(midpoint(x3,x4) == midpoint(x4,x3));
  }
  
  private static void question_3(){
    System.out.println("\nQ3");
    System.out.println(midpt2(x1,x1));
    System.out.println(midpt2(x1,x2));
    System.out.println(midpt2(x1,x3));
    System.out.println(midpt2(x1,x4));
    System.out.println(midpt2(x2,x1));
    System.out.println(midpt2(x2,x2));
    System.out.println(midpt2(x2,x3));
    System.out.println(midpt2(x2,x4));
    System.out.println(midpt2(x3,x1));
    System.out.println(midpt2(x3,x2));
    System.out.println(midpt2(x3,x3));
    System.out.println(midpt2(x3,x4));
    System.out.println(midpt2(x4,x1));
    System.out.println(midpt2(x4,x2));
    System.out.println(midpt2(x4,x3));
    System.out.println((midpt2(x4,x4))+ "\n");
  }
  
  private static void question_4(){
    System.out.println("\nQ4");
    System.out.println(midpt3(x1,x1));
    System.out.println(midpt3(x1,x2));
    System.out.println(midpt3(x1,x3));
    System.out.println(midpt3(x1,x4));
    System.out.println(midpt3(x2,x1));
    System.out.println(midpt3(x2,x2));
    System.out.println(midpt3(x2,x3));
    System.out.println(midpt3(x2,x4));
    System.out.println(midpt3(x3,x1));
    System.out.println(midpt3(x3,x2));
    System.out.println(midpt3(x3,x3));
    System.out.println(midpt3(x3,x4));
    System.out.println(midpt3(x4,x1));
    System.out.println(midpt3(x4,x2));
    System.out.println(midpt3(x4,x3));
    System.out.println((midpt3(x4,x4))+ "\n");
  }
  
  private static void question_5(){
    Integer i = Integer.MAX_VALUE;
    System.out.println(i);
    System.out.println(midpoint(-i,i)); // works because the value never goes over int max
    System.out.println(midpt2(-i,i)); // fails - int max minus negative int max exceed int max
    System.out.println(midpt3(-i,i)); // fails - int max minus negative int max exceed int max
    System.out.println();
  }
  
  private static void question_6(){
    // unsafe is ok when the int will be small
    // unsafe is neccessary when int could be very large
  }
  
  private static void question_7(){
    float x = 3.0f, y = 4.0f, z = 5.0f;
    for ( int i = 0; i < 20; i++){
      float e = new Double((hyp(x,y)-z)/z).floatValue();
      System.out.println(i + " " + e); // when multiplying larger numbers it rounds incorrectly
      x *= 10.0f; y *= 10.0f; z *= 10.0f;
    }
    System.out.println(Float.MAX_VALUE);
  }
  
  private static void question_8(){
    float x = 3.0f, y = 4.0f, z = 5.0f;
    for ( int i = 0; i < 20; i++){
      float e = new Double((hyp(x,y)-z)/z).floatValue();
      System.out.println(i + " " + e); // when multiplying larger numbers it rounds incorrectly
      x *= 10.0f; y *= 10.0f; z *= 10.0f;
    }
    System.out.println(Float.MAX_VALUE);
  }
  
  private static int midpoint(int x1, int x2){
    int midpointx = (x1+x2)/2;
    return midpointx;
  }
  
  private static int midpt2(int x1, int x2){
    return x1 + ((x2-x1)/2);
  }
  
  private static int midpt3(int x1, int x2){
    return x1 <= x2? (x2-x1)/2 + x1 : (x1-x2)/2 + x2;
  }
  
  private static double hyp(float x, float y){
    //System.out.println(x*x + " " + y*y + " " + (x*x + y*y));
    return Math.sqrt((x*x) + (y*y));
  }
  
}
  
  