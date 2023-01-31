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
   * @param time        The time this event occurs.
   * @param customer    The customer associated with this event.
   * @param bank        The bank the customer is at.
   */
  public Arrival(double time, Customer customer, Bank bank) {
    super(time);
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
    String str = customer.toString() + " arrives";
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
    Counter availableCounter = bank.availableCounter();

    if (availableCounter == null) {
      // If no such counter can be found, the customer
      // should depart.
      return new Event[] { 
        new Departure(this.getTime(), this.customer)
      };
    } else {
      // Else, the customer should go to the first 
      // available counter and get served.
      return new Event[] { 
        new ServiceBegin(this.getTime(), this.customer, availableCounter)
      };
    }
  }
}
