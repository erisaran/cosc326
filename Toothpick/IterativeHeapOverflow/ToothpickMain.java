/** Main file for drawing a series of toothpicks
  * becomes noticable slow with an n of 23
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
        System.out.println("Argument must be an integer");
      }
    }
    count = n;
    ratio = l;
    //scale /= (n/2 + 1)*(l) > 1 ? (n/2 + 1)*(l) : 1;
    Double ld = new Float(l).doubleValue();
    if (l <= 1) scale /= Math.pow((n*ld*ld *ld)/2 + 1,ld*ld);
    else {
      for (int a = 0; a < n; a++) scale /= ld;
    }
    //scale /= Math.pow(n/2 + 1,ld*ld); 
    System.out.println(scale);
      
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
    
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      ArrayList<Toothpick> t = new ArrayList<Toothpick>();
      t.add(new Toothpick(scale));
      t.get(0).display(g);
      for (int i = 0; i < n; i++){
        ArrayList<Toothpick> s = new ArrayList<Toothpick>();
        for (Toothpick c : t){
          s.add(new Toothpick(c.getX(0),c.getY(0),l,c.getSize(),c.getHor()));
          s.add(new Toothpick(c.getX(1),c.getY(1),l,c.getSize(),c.getHor()));
        }
        t = s;
        for (Toothpick p : t){
          p.display(g);
        }
      }
    }
    
  }
}