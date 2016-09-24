import java.util.ArrayList;

class Penny {
  
  private static int [] [] [] board;
  private static int [] [] moves = {{0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1}, {-1,0},{-1,-1}};
  private static ArrayList<Integer> been = new ArrayList<Integer>();
  
  public static void main(String [] args) {
    
    /* [ 0 ] [ 1 ] [ 2 ]         [ -1,-1 ] [ 0,-1 ] [ 1,-1 ]
     * [ 3 ]       [ 4 ]         [ -1,0  ]          [ 1,0 ]
     * [ 5 ] [ 6 ] [ 7 ]         [ -1,1  ] [ 0, 1 ] [ 1,1 ]
     */
    
    board = new int [] [] [] {{{0,4},{3,4},{0,4,5}},{{1,2},{},{0,5}},{{6,3},{0,3,7},{4,5,6}}};
    been.add(2200);
    play(0,0,2,2,1);
    
  }
  
  private static boolean play(int p1x, int p1y, int p2x, int p2y, int turn){
    System.out.print("Turn: " + turn);
    int [] turns;
    int [] turnat;
    int pfx;
    int pfy;
    if (turn % 2 == 1){
      turns = board[p2x][p2y];
      pfx = p2x; pfy = p2y;
      turnat = new int [] {p1x,p2y};
      System.out.print(" Move penny 1 ");
    }else {
      turns = board[p1x][p1y];
      pfx = p1x; pfy = p1y;
      turnat = new int [] {p2x,p2y};
      System.out.print(" Move penny 2 ");
    }
    for (int i = 0; i < turns.length; i++){
      int t = turns[i];
      int px = turnat[0] + moves[t][0];
      int py = turnat[1] + moves[t][1];
      if (px >= 0 && px < 3 && py >= 0 && py < 3){
        int beento = px + py * 10 + pfx * 100 + pfy * 1000;
        boolean beenthere = false;
        for (int q = 0; q < been.size(); q ++){
          if (been.get(q) == beento) beenthere = true;
        }
        System.out.println(beento + " ");
        been.add(beento);
        if (px == 1 && py == 1){
          return true;
        }
        if (!beenthere && px != pfx && py != pfy){
          System.out.println("try " + moves[t][0] + " " + moves[t][1]);
          play(px, py,pfx,pfy,turn + 1);
        }
      }
    }
    return false;
  }
  
}