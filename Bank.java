/**
 * This class implements a Bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class Bank {
  // Number of operational counters at the bank.
  private int numOfCounters; 

  // Operational counters at the bank.
  private Counter[] counters;
  
  // Queue at the bank.
  private Queue queue;

  public Bank(int numOfCounters, Queue queue) {
    this.numOfCounters = numOfCounters;
    this.queue = queue;

    // Initialises number of counters.
    this.counters = new Counter[this.numOfCounters];
    for (int i = 0; i < this.numOfCounters; i++) {
      this.counters[i] = new Counter(i, true);
    }
  }
  
  // The first available counter at the bank.
  public Counter availableCounter() {
    Counter available = null;

    // Finds the first available counter in the array of counters.
    for (int i = 0; i < this.counters.length; i += 1) {
      if (this.counters[i].getAvailability()) {
        available = counters[i];
        break;
      }
    }

    return available;
  }

  // Queue
  public String printQueue() {
    return this.queue.toString();
  }

  public boolean queueIsFull() {
    return this.queue.isFull();
  }

  public boolean queueIsEmpty() {
    return this.queue.isEmpty();
  }

  public boolean addCustomer(Customer customer) {
    return this.queue.enq(customer);
  }
  
  public Customer removeCustomer() {
    return (Customer) this.queue.deq();
  }

}

