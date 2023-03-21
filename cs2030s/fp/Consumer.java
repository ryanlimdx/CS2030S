package cs2030s.fp;

/**
 * Represents a function that consumes a value.
 * CS2030S Lab 6
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 *
 * @param <T> The type of the object to be consumed.
 */

public interface Consumer<T> {

  public void consume(T obj);
}
