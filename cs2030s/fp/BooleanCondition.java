/**
 * A conditional statement that returns either true of false.
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 */

package cs2030s.fp;

// needs to indicate public, otherwise will default to protected.
public interface BooleanCondition<T> {
  
  public boolean test(T argument);
}
