/**
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {

  private static class None extends Maybe<Object> {
    private static final None NONE = new None();

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object otherNone) {
      if (otherNone instanceof None) {
        return true;
      }
      return false;
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override 
    public Maybe<Object> filter(BooleanCondition<Object> condition) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<Object, ? extends U> transformer) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<Object, ? extends Maybe<? extends U>> transformer) {
      return Maybe.none();
    }
    
    @Override
    public Object orElse(Object defaultValue) {
      return defaultValue;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    @Override
    public void ifPresent(Consumer<Object> consumer) {
    }
  }

  private static class Some<T> extends Maybe<T> {
    private final T content;
    
    private Some(T content) {
      this.content = content;
    }

    @Override
    public String toString() {
      return "[" + get() + "]";
    }

    @Override
    public boolean equals(Object b) {
      if (b instanceof Some<?>) {
        Some<?> otherSome = (Some<?>) b;

        if (get() == otherSome.get()) {
          return true;
        }

        if (otherSome.get() == null || get() == null) {
          return false;
        }

        return get().equals(otherSome.get());
      }

      return false;
    }

    @Override
    protected T get() {
      return this.content;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (get() != null && !condition.test(get())) {
        return Maybe.none();
      } else {
        return this;
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      U newContent = transformer.transform(get());
      return new Some<U>(newContent);
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer) {
      // transformer returns an object of type Maybe. 
      @SuppressWarnings("unchecked")
      Maybe<U> newMaybe = (Maybe<U>) transformer.transform(get());
      return newMaybe;
    }

    @Override
    public T orElse(T defaultValue) {
      return get();
    }
    
    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return get();
    }

    @Override
    public void ifPresent(Consumer<? super T> consumer) {
      consumer.consume(get());
    }
  }

  public static <T> Maybe<T> none() {
    // an empty object can be of any type.
    @SuppressWarnings("unchecked")
    Maybe<T> none = (Maybe<T>) None.NONE;
    return none; 
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T content) {
    if (content != null) {
      return some(content);
    } else {
      return none();
    }
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, 
      ? extends Maybe<? extends U>> transformer);
  
  public abstract T orElse(T defaultValue);

  public abstract T orElseGet(Producer<? extends T> producer);

  public abstract void ifPresent(Consumer<? super T> consumer);
}
    

