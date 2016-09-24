class DiceGame extends Rollin {
  
  public int decideRoll(int roll) {
    
    int og_num;
    int og_i;
    for(int i=0;i<=5;i++){
      og_i = i;
      og_num = dice[i];
      dice[i] = roll;
      if(isComplete()){
        return og_i;
      }else{
        dice[og_i] = og_num;
      }
    }
    return rng.nextInt(8);
  }
  
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