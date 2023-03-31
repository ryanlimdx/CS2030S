package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;

/**
 * A list with a possibly infinite number of elements enabled by lazy evaluation.
 *
 * @author Ryan Lim Ding Xuan (10J)
 *
 * @param <T> The type of the content to be wrapped in Lazy that is an element of the list.
 */
public class InfiniteList<T> {
  /** The unevaluated element of the list, to be evaluated when needed. */
  private final Lazy<Maybe<T>> head;
  /** The tail of the lazy list. */
  private final Lazy<InfiniteList<T>> tail;

  /** A private constructor to initialise an empty infinite list. */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /** 
   * Generates a lazy list of the same elements that is possibly infinite. 
   * 
   * @param <T> The type of the content in the lazy list.
   * @param producer The producer to produce the elements of the lazy list.
   * @return The created lazy infinite list.
   * */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    return new InfiniteList<T>(
        Lazy.of(() -> Maybe.some(producer.produce())),
        Lazy.of(() -> generate(producer))
        );
  }

  /**
   * Passes in a seed as the first element and uses the producer to produce 
   * the subsequent elements when evaluated.
   *
   * @param <T> The type of the content in the lazy list.
   * @param seed The seed to pass into the transformer.
   * @param next The transformer to transform the previous element.
   * @return The created lazy infinite list.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(seed, () -> iterate(next.transform(seed), next));
  }

  /** 
   * A private constructor for an infinite list with an already evaluated head.
   * 
   * @param head The head of the infinite list, to be wrapped.
   * @param tail The tail of the infinite list, to be wrapped.
   */ 
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(() -> tail.produce());
  }

  /** 
   * A private constructor for an infinite list with an unevaluated head. 
   *
   * @param head The head of the infinite list.
   * @param tail The tail of the infinite list.
   */ 
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Gets and evaluates the head of the infinite list. Skips empty values.
   *
   * @return The evaluated head.
   */
  public T head() {
    if (this.head == null) {
      return null;
    }

    return this.head
      .get() // returns a Maybe<T>
      .orElseGet(() -> this.tail.get().head()); // returns value in Maybe<T>

  }

  /**
   * Gets the tail of the infinite list. Skips empty values.
   *
   * @return The tail of the infinite list.
   */
  public InfiniteList<T> tail() {
    if (this.tail == null) { 
      return null;
    }

    return this.head
      .get() 
      .map(x -> this.tail.get())
      .orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Produces a new lazy infinite list with its content modified by a transformer.
   *
   * @param <R> The output type after the content is passed into the transformer.
   * @param mapper The function to be applied to the previous element.
   * @return A new infinite list.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<R>(
        // Lazy.of(() -> Maybe.some(mapper.transform(this.head()))),
        this.head.map(maybeH -> maybeH.map(mapper)),
        // Lazy.of(() -> this.tail().map(mapper))
        this.tail.map(infTail -> infTail.map(mapper))
        );
  }

  /**
   * Determines if the content passes the condition and returns a new infinite list.
   *
   * @param predicate The condition for the object.
   * @return A new infinite list that produces filtered elements.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T>(
        this.head.map(maybeH -> maybeH.filter(predicate)),
        this.tail.map(infTail -> infTail.filter(predicate))
        );
  }

  /** A nested static class to make an infinite list finite. */
  private static class Sentinel extends InfiniteList<Object> {
    /** The marker for the end of the list. */
    private static final Sentinel SENTINEL = new Sentinel();

    /** A public constructor to initialise a sentinel. */ 
    public Sentinel() {
      super();
    }

    @Override
    public boolean isSentinel() {
      return true;
    }

    @Override
    public Object head() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.sentinel();
    }

    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    @Override
    public long count() {
      return 0;
    }

    @Override
    public List<Object> toList() {
      return List.of();
    }

    @Override
    public String toString() {
      return "-";
    }
  }

  /**
   * Returns a sentinel to demarcate the end of an infinite list.
   *
   * @param <T> The type of the sentinel class.
   * @return The marker to denote the end of the list.
   */
  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> temp = (InfiniteList<T>) Sentinel.SENTINEL;
    return temp;
  }

  /**
   * Determines if it is the end of the list.
   *
   * @return The boolean to determine if it is a sentinel.
   */
  public boolean isSentinel() {
    if (this instanceof Sentinel) {
      return true;
    }

    return false;
  }

  /**
   * Set the length of the infinite list.
   *
   * @param n The length of the infinite list.
   * @return The infinite list of size n.
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return sentinel();
    }

    return new InfiniteList<T>(
        this.head,
        this.head  
        .map(maybeH -> maybeH
          .map(x -> this.tail.get().limit(n - 1))
          .orElseGet(() -> this.tail.get().limit(n))
          )
        );
  }

  /**
   * Takes the element of the list while the condition is fulfilled. Once an element fails,
   * truncate the list.
   *
   * @param predicate The condition for the object.
   * @return The to-be-truncated infinite list.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {

    Lazy<Maybe<T>> newHead = this.head.map(maybeH -> 
        Maybe.some(this.head()).filter(predicate));

    return new InfiniteList<T>(
        newHead,
        newHead
        .map(maybeH -> maybeH
          .map(x -> this.tail().takeWhile(predicate))
          .orElseGet(() -> sentinel())
          )
        );
  }

  /**
   * Accumulates the elements of the list; The infinite list has to have limit called on it.
   * 
   * @param <U> The type of the element returned by accumulating the list.
   * @param identity The base value.
   * @param accumulator The function to combine the elements of the list.
   * @return The accumulated value after passing in the list to the accumulator.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    U result = identity;
    List<T> list = this.toList();

    for (T next : list) {
      result = accumulator.combine(result, next);
    }

    return result;
  }

  /**
   * Counts the length of the list.
   *
   * @return The length of the list.
   */
  public long count() {
    return this.toList().size();
  }

  /**
   * Converts the infinite list into a list.
   *
   * @return A list.
   */
  public List<T> toList() {
    List<T> list = new ArrayList<T>();
    InfiniteList<T> infList = this;

    while (!infList.isSentinel()) {
      infList.head.get().consumeWith(x -> list.add(x));
      infList = infList.tail.get();
    }

    return list;
  }

  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
