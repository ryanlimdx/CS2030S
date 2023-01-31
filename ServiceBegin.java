/**
 * This class implements service begin, after a customer arrives
 * at an available counter of the bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class ServiceBegin extends Event {
  // The customer's details.
  private Customer customer;

  // The details of the counter associated with this event.
  private Counter counter;

  /**
   * Constructor for a service begin event.
   *
   * @param time       The time this event occurs.
   * @param customer   The customer associated with this event.
   * @param counter    The counter associated with this event.
   */
  public ServiceBegin(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  /**
   * Returns the string representation of the Service Begin event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = customer.toString() + " service begin " + counter.toString();
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
    double endTime = this.customer.endServiceTime(this.getTime());
    return new Event[] { 
      new ServiceEnd(endTime, this.customer, this.counter)
    };
  }

}
