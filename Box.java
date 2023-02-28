/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 */

public class Box<T> {

  private final T content;

  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T content) {
    this.content = content;
  }

  public static <T> Box<T> of(T content) {
    if (content == null) {
      return null;
    } else {
      return new Box<T>(content);
    }
  }

  public static <T> Box<T> ofNullable(T content) {
    if (content == null) {
      return empty();
    } else {
      return new Box<T>(content);
    }
  }

  @Override
  public boolean equals(Object otherB) {
    if (this == otherB) {
      return true;
    }

    if (otherB instanceof Box<?>) {
      Box<?> b = (Box<?>) otherB;
      if (this.content == b.content) {
        return true;
      }

      if (this.content == null || b.content == null) {
        return false;
      }

      return this.content.equals(b.content);
    }

    return false;
  }

  @Override
  public String toString() {
    if (this.content == null) {
      return "[]";
    } else {
      String stringifyContent = String.valueOf(this.content);
      return "[" + stringifyContent + "]";
    }
  } 
  
  public static <T> Box<T> empty() {
    // Empty boxes can be casted to different types.
    @SuppressWarnings("unchecked")
    Box<T> emptyBox = (Box<T>) EMPTY_BOX;
    return emptyBox;
  }

  public boolean isPresent() {
    return this.content != null;
  }

  public Box<T> filter(BooleanCondition<? super T> conditions) {
    if (this.content != null) {
      if (conditions.test(this.content)) {
        return this;
      } else {
        return empty();
      }
    } else {
      return empty();
    }
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    if (this.content == null) {
      return empty();
    } else {
      U newContent = transformer.transform(this.content);
      return new Box<U>(newContent);
    }
  }
}
