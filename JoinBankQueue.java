/**
 * This class implements JoinBankQueue for when a
 * customer joins the queue at the bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 
class JoinBankQueue extends Event {

  // The bank associated with this event. 
  private Bank bank;

  // The customer associated with this event.
  private Customer customer;

  public JoinBankQueue(Customer customer, Bank bank) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.bank = bank;
  }

  /**
   * Returns the string representation of the join bank queue event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = ": " + this.customer + " joined bank queue " + this.bank;
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
    this.bank.addCustomer(this.customer);
    return new Event[] {};
  }
}
