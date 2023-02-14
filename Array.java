/**
 * The Array<T> for CS2030S 
 *
 * @author Ryan Lim Ding Xuan 
 * @version CS2030S AY22/23 Semester 2
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  public Array(int size) {     
    // For 'unchecked' warning, the only way we can put an object 
    // into array is through the method set() and we only put 
    // object of type T inside. So it is safe to cast `Comparable[]`
    // to `T[]`. For 'rawtypes', when initialising a comparable array, 
    // 'rawtypes' warning will appear due to type erasure. Hence, it 
    // can be suppressed.
    @SuppressWarnings({"rawtypes", "unchecked"})
    T[] arr = (T[]) new Comparable[size];
    this.array = arr;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    if (this.array.length == 0) {
      return null;
    } else {
      T t = this.array[0];
      for (int i = 0; i < this.array.length; i++) {
        if (t.compareTo(this.array[i]) == 1) {
          t = this.array[i];
        }
      }
      return t;
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
