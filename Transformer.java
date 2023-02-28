/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 */

public interface Transformer<T, U> {

  public U transform(T argument);
}
