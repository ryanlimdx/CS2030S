/** 
 * This class implements service begin, after a customer arrives 
 * at an available counter of the bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class ServiceBegin extends Event {
  // The customer's details.
  private Customer customer;

  // The bank the customer is at.
  private Bank bank;
  
  // The details of the counter associated with this event.
  private Counter counter;

  /**
   * Constructor for a service begin event.
   *
   * @param customer   The customer associated with this event.
   * @param bank       The bank the customer is at.
   * @param counter    The counter servicing the customer.
   */
  public ServiceBegin(Customer customer, Bank bank, Counter counter) {
    super(customer.getServiceStartTime());
    this.customer = customer;
    this.bank = bank;
    this.counter = counter;
  }

  /**
   * Returns the string representation of the Service Begin event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = ": " + this.customer + " " + 
        this.customer.getAction() + " begin " + this.counter;
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
    // The current event is a service-begin event.  
    // Mark the counter is unavailable, then schedule
    // a service-end event.
    this.counter.setAvailability(false);
    return new Event[] { 
      new ServiceEnd(this.customer, this.bank, this.counter)
    };
  }

}
