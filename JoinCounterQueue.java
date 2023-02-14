/**
 * This class implements JoinCounterQueue for when a
 * customer joins the queue at a counter.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 
class JoinCounterQueue extends Event {

  // The counter associated with this event. 
  private Counter counter;

  // The customer associated with this event.
  private Customer customer;

  public JoinCounterQueue(Customer customer, Counter counter) {
    super(customer.getStartTime());
    this.customer = customer;
    this.counter = counter;
  }

  /**
   * Returns the string representation of the join counter queue event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = ": " + this.customer + " joined counter queue (at " 
        + this.counter + ")";
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
    this.counter.addCustomer(this.customer);
    return new Event[] {};
  }
}
