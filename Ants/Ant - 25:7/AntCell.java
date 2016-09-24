public class AntCell {

 private String c;
 private int x;
 private int y;
 private AntCell p;

 public AntCell(String colour) {
  this.x = 0;
  this.y = 0;
  this.c = colour;
  this.p = null;
 }

 public AntCell(String colour, int step, AntCell previous) {
  this.c = colour;
  if (step == 1) {
   this.x = previous.getX() + 1;
   this.y = previous.getY();
  } else if (step == -1) {
   this.x = previous.getX() - 1;
   this.y = previous.getY();
  } else if (step == 2) {
   this.x = previous.getX();
   this.y = previous.getY() + 1;
  } else {
   this.x = previous.getX();
   this.y = previous.getY() - 1;
  }
  p = previous;

 }

 public int getX() {
  return this.x;
 }

 public int getY() {
  return this.y;
 }

 public String getC() {
  return this.c;
 }

 public void setC(String s) {
  this.c = s;
 }

 public AntCell getP() {
  return this.p;
 }

}