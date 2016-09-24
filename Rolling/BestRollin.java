/*Authors:
 * Thomas Crisp
 * Murdoch Braid
 * Ben Dutton
 * Francesco Lee
 * Wednesday 20 July, 2016
 */

class BestRollin extends Rollin {
  
  /* if the set can be completed in one turn call return the index of the dice to replace
   * otherwise use an algorithm to determine what position would be best to replace with the current roll
   * in order to create a set with the highest likelihood of completion
   * @param roll takes the current dice roll
   * @return the index of the dice to be swapped
   */
  public int decideRoll(int roll) {
    if (finishState(roll) > 0){
      return finishState(roll);
    }else {
      return bestMove(roll);
    }
  }
  
  /** This method decides whether to accept the newly rolled dice or not.
    * It will either return the index of the old dice to replace,
    * or will randomly return an dice index to replace (or, if 0, to replace none).
    */
  private int finishState(int n){
    int og_num;//original dice value
    int og_i;//original dice index
    
    for(int i=0;i<=5;i++){// for loop increments through dice array
      og_i = i;
      og_num = dice[i];
      dice[i] = n;// replaces a dice with the new roll
      if(isComplete()){//checks if that makes 2 complete sets
        dice[og_i] = og_num;
        return og_i;//returns index of dice to replace if it does
      }else{
        dice[og_i] = og_num;//otherwise return dice array to original state
      }
    }
    return 0;
  }
  
  /* This method determines the optimal set it can create from the current set and the current dice roll
   * @param the current dice roll
   * @return the index of the dice to be replaced, -1 if no replacement
   */
  private int bestMove(int n){
    int bestChanceIndex = -1; //index of best dice to replace
    int [] chanceCounts = new int[diceInGame]; // counts the chance value of each replacement option
    int highest = 0; //keeps track of the highest chance
    
    // this loop checks the chance of each potential set of dice that can be made with the current roll
    for (int i = 0; i < chanceCounts.length; i++){ 
      int store = dice[i]; //stores the value of the original set at the index which is being manipulated
      dice[i] = n;// changes the array to a potential option
      chanceCounts[i] = countChance(); //calls a method to evalute the chance value of this possible choice
      dice[i] = store; //resets the set of dice to what we had before
    }
    
    // this loop finds the highest chance value amongst the potential next sets
    for (int j = 0; j < chanceCounts.length; j++){
      if (chanceCounts[j] > highest){ // if the current index produces a better chance update the highest
        highest = chanceCounts[j]; 
        bestChanceIndex = j;
      }
    }
    if (highest < countChance()){ //if not swapping any dice is better then return -1 
      return -1;
    }else{ //else there is a better set of dice that can be created and the optimal one should be created
      /*int secondDegreeHighest = 0;
      int secondDegreeIndex = 0;
      for (int k = 0; k < chanceCounts.length; k++){
        if (chanceCounts[k] == highest){
          int store1 = dice[k];
          dice[k] = n;
          for (int z = 1; z < facesPerDie; z ++){
            for (int y = 0; y < chanceCounts.length; y++){
              int store2 = dice[y];
              dice[y] = z;
              if (secondDegreeHighest < countChance()){
                secondDegreeHighest = countChance();
                secondDegreeIndex = k;
              }
              dice[y] = store2;
            }
          }
          dice[k] = store1;
        }
        
      }
      bestChanceIndex = secondDegreeIndex;*/
      //System.out.println("Best next turn " + highest);
      return bestChanceIndex;
    }
  }
  
  /* This method counts how many dice rolls on the next turn could complete the set
   * and returns a chance value
   * @return and int to represent how many of the potential dice rolls could complete the set
   */
  private int countChance(){
    int chance = 0; //keeps track of a chance value
    // checks through each possible outcome of the next roll
    for (int a = 1; a <= facesPerDie;a++){
      //searches through each of the potential replacements to see if they are completeable
        for (int b = 0; b < diceInGame; b++){
          int store = dice[b];
          dice[b] = a;
          //System.out.println();
          if (isComplete()){ //adds 1 to the chance value if this potential set can be completed
            chance++;
            dice[b] = store;
            //System.out.println("broke");
            break;
          }
          //System.out.println("not broke");
          dice[b] = store; //resets the set of dice
        }
      }
    //System.out.println(chance);
    return chance;
  }
  
  
  /* main method that uses the original code in a for loop that we made to test the average and the max
   */
  public static void main(String[] args) {
    double turnCount = 0;
    double times = 100000;
    int max = 0;
    for (int i = 0; i < times; i ++){ // runs the game multiple times and gets an average
      
      Rollin test = new BestRollin();
      int turns = 0;
      while (!test.isComplete()) {
        test.turn();
        turns++;
        
      }
      //System.out.println(turns);
      //System.out.println();
      turnCount += turns;
      if (turns > max){
        max = turns;
      }
      
    }
    System.out.println("average " + turnCount/times + " max " + max);
  }
}