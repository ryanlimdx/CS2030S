package cs2030s.fp;

/**
 * A wrapper around a lazily evaluated object that
 * can be generated with a lambda expression.
 *
 * @author Ryan Lim Ding Xuan (10J)
 * @version CS2030S AY22/23 Sem 2
 *
 * @param <T> The type of the content to be wrapped in Lazy.
 */
public class Lazy<T> {
  /** The unevaluated object, to be evaluated once when needed. */
  private Producer<? extends T> producer;
  /** The wrapped, cached object. */
  private Maybe<T> value;

  /**
   * A private constructor to intialize the value given one.
   *
   * @param value The given value to be initialised.
   */
  private Lazy(Maybe<T> value) {
    this.value = value;
  }
  
  /**
   * A private constructor to initialize a delayed evaluation given one.
   *
   * @param s The given producer to be initialised.
   */
  private Lazy(Producer<? extends T> s) {
    this.value = Maybe.none();
    this.producer = s;
  }
  
  /**
   * Initialises the lazy object with the given value.
   *
   * @param <T> The type of the content given.
   * @param v The content of lazy.
   * @return The created lazy object.
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(Maybe.some(v));
  }

  /**
   * Initialises the lazy object with the given producer 
   * that produces a value when needed.
   *
   * @param <T> The type of the content to be evaluated.
   * @param s The producer to delay the evaluation of the content.
   * @return The created lazy object.
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  /**
   * Gets the value if needed; If already available, skip the computation 
   * and return the value immediately.
   *
   * @return The content of the lazy object.
   */
  public T get() {
    T content = this.value.orElseGet(this.producer);
    this.value = Maybe.some(content);
    return content;
  }
  
  /**
   * Returns the string representation of the value if it is available, otherwise return "?".
   *
   * @return The string representation of the content.
   */
  @Override
  public String toString() {
    return this.value.map(x -> String.valueOf(x)).orElse("?");
  }

  /** 
   * Produces a new Lazy object with its content modified by a transformer.
   *
   * @param <U> The output type after the content is passed into the transformer.
   * @param transformer The function to be applied to the previous element.
   * @return A new lazy object wrapping the output element.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    Producer<U> newProducer = () -> transformer.transform(this.get());
    return Lazy.of(newProducer);
  }

  /** 
   * Produces a new Lazy object with its content modified by a transformer
   * without wrapping the content.
   *
   * @param <U> The output type after the content is passed into the transformer.
   * @param transformer The function to be applied to the previous element.
   * @return A new lazy object with the output element.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    // first get() produces a Lazy, second get() produces the content.
    Producer<U> newProducer = () -> transformer.transform(this.get()).get();
    return Lazy.of(newProducer);
  }
  
  /**
   * Determines if the content passes the condition.
   *
   * @param condition The condition for the object.
   * @return Whether the object passed the condition.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    Producer<Boolean> newProducer = () -> condition.test(this.get());
    return Lazy.of(newProducer);
  }
  
  /**
   * Determines if two objects are equivalent to each other.
   *
   * @param otherLazy The other object to be compared against.
   * @return Whether the other object is also a lazy object.
   */
  @Override
  public boolean equals(Object otherLazy) {
    if (otherLazy instanceof Lazy<?>) {
      Lazy<?> b = (Lazy<?>) otherLazy;
      this.get(); // initialises the type Maybe value.
      b.get(); // initialises the other Lazy's type Maybe value.
      return this.value.equals(b.value); 
    }

    return false;
  }

  /**
   * Combines two lazy objects together by an operation.
   *
   * @param <S> The type of the other lazy object.
   * @param <R> The type of the resulting lazy object after combining.
   * @param lazyObj The other lazy object to be combined together.
   * @param combiner Function to combine the two lazy objects.
   * @return The new lazy object after combining the two.
   */
  public <S, R> Lazy<R> combine(Lazy<S> lazyObj, 
      Combiner<? super T, ? super S, ? extends R> combiner) {
    Producer<R> newProducer = () -> combiner.combine(this.get(), lazyObj.get());
    return Lazy.of(newProducer);
  }

}
