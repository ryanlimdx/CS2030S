/**
 * This class implements a Bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class Bank implements QueueActions {
  // Number of operational counters at the bank.
  private int numOfCounters; 

  // Operational counters at the bank.
  private Array<Counter> counters;
  
  // Queue at the bank.
  private Queue<Customer> queue;

  public Bank(int numOfCounters, int maxBankQueue, int maxCounterQueue) {
    this.numOfCounters = numOfCounters;
    this.queue = new Queue<Customer>(maxBankQueue);

    // Initialises number of counters.
    this.counters = new Array<Counter>(numOfCounters); 
    for (int i = 0; i < this.numOfCounters; i++) {
      Counter counter = new Counter(i, true, maxCounterQueue);
      counters.set(i, counter);
    }
  }
  
  // The first available counter at the bank.
  public Counter availableCounter() {
    Counter available = null;

    // Finds the first available counter in the array of counters.
    for (int i = 0; i < this.numOfCounters; i += 1) {
      if (this.counters.get(i).getAvailability()) {
        available = counters.get(i);
        break;
      }
    }

    return available;
  }
  
  // Finds the counter with the shortest queue.
  public Counter shortestCounterQueue() {
    Counter counterShortestQueue = counters.min();
    if (counterShortestQueue.queueIsFull()) {
      return null;
    } else {
      return counterShortestQueue;
    }
  }

  @Override
  public String toString() {
    return this.queue.toString();
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

}

