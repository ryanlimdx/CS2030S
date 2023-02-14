/**
 * This class implements Arrival for when a 
 * customer arrives at the bank. 
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class Arrival extends Event {
  // The customer's details.
  private Customer customer; 

  // The bank customer is arriving at.
  private Bank bank;

  /**
   * Constructor for a bank arrival event.
   *
   * @param customer    The customer associated with this event.
   * @param bank        The bank the customer is at.
   */
  public Arrival(Customer customer, Bank bank) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.bank = bank;
  }

  /**
   * Returns the string representation of the arrival event.
   *
   * @return A string representing the arrival event.
   */
  @Override
  public String toString() {
    String str = ": " + this.customer + " arrived " + this.bank;
    return super.toString() + str;
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    Counter availableCounter = this.customer.goToCounter(this.bank);
    
    // Once service ends, next customer will be called. Since there is an available counter,
    // this means that no customers are waiting hence should proceed straight to counter.
    if (availableCounter == null) {
      Counter counterShortestQueue = this.bank.shortestCounterQueue();
      if (counterShortestQueue == null && this.bank.queueIsFull()) {
        return new Event[] {
          new Departure(this.getTime(), this.customer)
        };
      } else if (counterShortestQueue == null) {
        return new Event[] {
          new JoinBankQueue(this.customer, this.bank)
        };
      } else {
        return new Event[] {
          new JoinCounterQueue(this.customer, counterShortestQueue)
        };
      }
    } else {
      return new Event[] {
          new ServiceBegin(this.customer, this.bank, availableCounter)
      };
    }
  } 

}
