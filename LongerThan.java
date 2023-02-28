/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 */

public class LongerThan implements BooleanCondition<String> {
  
  private int len;

  public LongerThan(int len) {
    this.len = len;
  }

  @Override
  public boolean test(String testString) {
    if (testString.length() > this.len) {
      return true;
    } else {
      return false;
    }
  }
}
