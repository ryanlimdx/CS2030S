/** 
 * This class implements a Counter at the bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class Counter implements Comparable<Counter>, QueueActions {
  // The id of the counter.
  private final int counterId;

  // Indicates availability of the counter.
  private boolean available;

  // Queue at the counter
  private Queue<Customer> queue;

  public Counter(int counterId, boolean available, int maxCounterQueue) {
    this.counterId = counterId;
    this.available = available;
    this.queue = new Queue<Customer>(maxCounterQueue);
  }
  
  @Override
  public String toString() {
    String str = String.valueOf(this.counterId) + " " + this.queue.toString();
    return "S" + str;
  }

  public boolean getAvailability() {
    return this.available;
  }

  public void setAvailability(boolean availability) {
    this.available = availability;
  }

  public int getQueueLength() {
    return this.queue.length();
  }
  
  @Override
  public boolean queueIsFull() {
    return this.queue.isFull();
  }
  
  @Override
  public boolean queueIsEmpty() {
    return this.queue.isEmpty();
  }

  @Override
  public boolean addCustomer(Customer customer) {
    return this.queue.enq(customer);
  }
  
  @Override
  public Customer removeCustomer() {
    return (Customer) this.queue.deq();
  }

  @Override
  public int compareTo(Counter counter) {
    if (this.queue.length() < counter.getQueueLength()) {
      return -1;
    } else if (this.queue.length() == counter.getQueueLength()) {
      return 0;
    } else {
      return 1;
    }
  }
}
