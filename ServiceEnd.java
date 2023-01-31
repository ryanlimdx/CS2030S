/**
 * This class implements service end, after a customer's
 * service has been completed.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class ServiceEnd extends Event {
  // The customer's details.
  private Customer customer;

  // The details of the counter associated with this event.
  private Counter counter;

  /**
   * Constructor for a service end event.
   *
   * @param time       The time this event occurs.
   * @param customer   The customer associated with this event.
   * @param counter    The counter associated with this event.
   */
  public ServiceEnd(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  /**
   * Returns the string representation of the Service End event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = customer.toString() + " service done " + counter.toString();
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
    // The current event is a service-end event.  
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    this.counter.setAvailability(true);
    return new Event[] { 
      new Departure(this.getTime(), this.customer),
    };
  }
}
