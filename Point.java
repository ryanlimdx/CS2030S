/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */
class Point {
  // TODO
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public String toString() {
    String strX = Double.toString(x);
    String strY = Double.toString(y);
    return "(" + strX + ", " + strY + ")";
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
  
  public void setX(double newX) {
    this.x = newX;
  }

  public void setY(double newY) {
    this.y = newY;
  }
}

