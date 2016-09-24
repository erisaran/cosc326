/** Main file for drawing a series of toothpicks
  * becomes noticably slow with an n of 23
  * 
  * Author - Benjaman Dutton
  */

import java.lang.NumberFormatException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToothpickMain extends JPanel{
  
  //private static ArrayList<Toothpick> t = new ArrayList<Toothpick>();
  private static int count; // count of how many generations
  private static float ratio; // size ratio between generations
  
  private static float scale = 600f;
  
  public static void main(String [] args){
    int n = 0;
    float l = 1;
    if (args.length > 0){
      try { 
        if (args.length == 2){
          l = Float.valueOf(args[1]); // l for how Long the next one in sequence is
        }
        n = Integer.valueOf(args[0]); // n for number of generations
      } catch (NumberFormatException e){
        System.out.println("Argument must be an number");
      }
    }
    count = n;
    ratio = l;
    Double ld = new Float(l).doubleValue();
    if (l <= 1) scale /= Math.pow((n*ld*ld *ld)/2 + 1,ld*ld);
    else for (int a = 0; a < n; a++) scale /= ld;
      
    // draw stuff
    JFrame frame = new JFrame("Toothpicks");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new ToothpickMain());
    frame.pack();
    frame.setVisible(true);
    
  }
  
  public ToothpickMain(){
    DrawingPanel draw = new DrawingPanel(count, ratio);
    add(draw);
    Toothpick p = new Toothpick(scale, ratio);
  }
  
  private class DrawingPanel extends JPanel{
    
    int n;
    float l;
    
    public DrawingPanel(int count, float ratio){
      setPreferredSize(new Dimension (1000, 1000));
      setBackground(Color.white);
      n = count;
      l = ratio;
    }
    
    /** searches through each node using a binary index, toothpicks don't need to be stored as they
      * are displayed upon creation and then destroyed
      */
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      Toothpick t = new Toothpick(scale, ratio);
      int pickCount = 1;
      t.display(g);
      for (int i = 1; i <= n; i++){
        System.out.println("Draw Gen " + i);
        int countj = 0;
        for (int j = 1; j <= Math.pow(2.0,i); j ++){
          t.getNode(j+pickCount,g);
          countj ++;
        }
        pickCount += countj;
      }
    }
    
  }
}