/**
 * This class implements Departure for when a
 * customer leaves the bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class Departure extends Event {
  // The customer's details.
  private Customer customer;

  /**
   * Constructor for a bank departure event.
   *
   * @param time       The time this event occurs.
   * @param customer   The customer associated with this event.
   */
  public Departure(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  /**
   * Returns the string representation of the departure event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = customer.toString() + " departed";
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
    return new Event[] {};
  }
}
