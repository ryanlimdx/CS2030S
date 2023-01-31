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
  
  public Bank(int numOfCounters) {
    this.numOfCounters = numOfCounters;

    // Initialises number of counters.
    this.counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter(i, true);
    }
  }
  
  // The first available counter at the bank.
  public Counter availableCounter() {
    Counter available = null;

    // Finds the first available counter in the array of counters.
    for (int i = 0; i < counters.length; i += 1) {
      if (counters[i].getAvailability()) {
        available = counters[i];
        break;
      }
    }

    return available;
  }
}

