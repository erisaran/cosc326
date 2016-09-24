/* Author Benjaman Dutton
 */

import java.util.ArrayList;

public class Harmonious {
  
  public static void main(String [] args){
    
    int highest = 6855552;
    int [] nums = new int[highest];
    ArrayList<Integer> pairs = new ArrayList<Integer>();
    
    for (int i = 2; i < highest/2; i++){
      for (int j = i+i; j < highest; j+=i){
        nums[j] += i;
      }
    }
    
    // find the highest number
    /*
    for (int i = 0; i < nums.length; i++){
      if (first[i] > highest){
        highest = nums[i];
      }
    }
    System.out.println(highest);
    */
    
    for (int i = 1; i < nums.length; i++){
      if (i <= 2000000 && i == nums[nums[i]] && i < nums[i]){
        pairs.add(i); pairs.add(nums[i]);
      }
    }
    for (int i = 0; i< pairs.size(); i+=2){
      System.out.println(pairs.get(i) + " " + pairs.get(i+1));
    }
  }
}