/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 */

public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  
  private int lastK;

  public LastDigitsOfHashCode(int lastK) {
    this.lastK = lastK;
  }

  @Override
  public Integer transform(Object obj) {
    int digits = Math.abs(obj.hashCode());
    // Safe to typecast as no decimal place for variable digits, 
    // therefore will have no lossy conversion to integer.
    int lastKDigits = (int) (digits % Math.pow(10, this.lastK));
    return lastKDigits;
  }
}
