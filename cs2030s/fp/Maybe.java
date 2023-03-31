package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * A Maybe wrapper class to eliminate if else checks.
 * CS2030S Lab 6
 * AY22/23 Semester 2
 *
 * @author Ryan Lim Ding Xuan (10J)
 */
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
    public <U> U orElse(U defaultValue) {
      return defaultValue;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    @Override
    public void ifPresent(Consumer<Object> consumer) {
    }
    
    @Override
    public void consumeWith(Consumer<Object> consumer) {
    }
  }

  private static class Some<T> extends Maybe<T> {
    private final T content;
    
    public Some(T content) {
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
    // protected to use that in the package
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
    public <U extends T> T orElse(U defaultValue) {
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
    
    @Override
    public void consumeWith(Consumer<? super T> consumer) {
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
  
  public abstract <U extends T> T orElse(U defaultValue);

  public abstract T orElseGet(Producer<? extends T> producer);

  public abstract void ifPresent(Consumer<? super T> consumer);
  
  /**
   * If the value within this Maybe is missing, do nothing. 
   * Otherwise, consume the value with the given consumer.
   *
   * @param consumer The consumer to consume the value 
   */
  public abstract void consumeWith(Consumer<? super T> consumer);
}
    

