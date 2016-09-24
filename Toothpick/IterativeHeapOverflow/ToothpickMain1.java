/** Main file for drawing a series of toothpicks
  * becomes noticable slow with an n of 23
  * Author - Benjaman Dutton
  */

import java.lang.NumberFormatException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToothpickMain1 extends JPanel{
  
  //private static ArrayList<Toothpick> t = new ArrayList<Toothpick>();
  private static int count;
  private static float ratio;
  
  private static float scale = 600f;
  
  public static void main(String [] args){
    int n = 0;
    float l = 1;
    if (args.length > 0){
      try {
        if (args.length == 2){
          l = Float.valueOf(args[1]);
        }
        n = Integer.valueOf(args[0]);
      } catch (NumberFormatException e){
        System.out.println("Argument must be an integer");
      }
    }
    count = n;
    ratio = l;
    scale /= n/2 + 1;
    
    //create starting toothpick
    /*
     t.add(new Toothpick(scale));
     int processed = 0;
     for (int i = 1; i <= n; i ++){
     int toCheck = t.size();
     for (int j = processed; j < toCheck; j++){
     Toothpick c = t.get(j);
     for (int k = 0; k < 2; k++){
     t.add(new Toothpick(c.getX(k),c.getY(k),l,c.getSize(),c.getHor()));
     }
     }
     processed = toCheck;
     }
     */
    
    // draw stuff
    JFrame frame = new JFrame("Toothpicks");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new ToothpickMain());
    frame.pack();
    frame.setVisible(true);
    
  }
  
  public ToothpickMain1(){
    DrawingPanel draw = new DrawingPanel(count, ratio);
    add(draw);
  }
  
  private class DrawingPanel extends JPanel{
    
    int n;
    float l;
    
    public DrawingPanel(int count, float ratio){
      setPreferredSize(new Dimension (900, 900));
      setBackground(Color.white);
      n = count;
      l = ratio;
    }
    
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      ArrayList<Toothpick> t = new ArrayList<Toothpick>();
      /*for (Toothpick p : t){
       p.display(g);
       }*/
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