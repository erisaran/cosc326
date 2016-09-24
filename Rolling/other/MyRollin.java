class MyRollin extends Rollin {
  
  public static void main(String[] args) {
    Rollin test = new MyRollin();
    int turns = 0;
    while (!test.isComplete()) {
      test.turn();
      turns++;
    }
    System.out.println(turns);
  }
}