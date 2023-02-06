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
    String str = ": " + this.customer + " arrived " + this.bank.printQueue();
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
    Counter availableCounter = this.bank.availableCounter();
    
    if (this.bank.queueIsFull()) { // checks if the queue is full
      return new Event[] {
        new Departure(this.getTime(), this.customer)
      };
    } else { // if not full, check if there is anybody queuing or if there is any empty counter
      if (!bank.queueIsEmpty() || availableCounter == null) {
        return new Event[] {
          new JoinQueue(this.customer, this.bank)
        };
      } else {
        return new Event[] {
          new ServiceBegin(this.customer, this.bank, availableCounter)
        };
      }
    }
  }

}
