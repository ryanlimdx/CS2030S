/**
 * This class implements service end, after a customer's
 * service has been completed.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class ServiceEnd extends Event {
  // The customer's details.
  private Customer customer;

  // The bank the customer is at.
  private Bank bank;

  // The details of the counter associated with this event.
  private Counter counter;

  /**
   * Constructor for a service end event.
   *
   * @param time       The time this event occurs.
   * @param customer   The customer associated with this event.
   * @param bank       The bank the customer is at.
   * @param counter    The counter associated with this event.
   */
  public ServiceEnd(Customer customer, Bank bank, Counter counter) {
    super(customer.serviceEndTime());
    this.customer = customer;
    this.bank = bank;
    this.counter = counter;
  }

  /**
   * Returns the string representation of the Service End event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = ": " + this.customer + " " + this.customer.getTask() 
        + " done (by " + this.counter + ")";
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
    
    if (!this.counter.queueIsEmpty()) {
      Customer counterQueueCustomer = this.counter.removeCustomer();
      counterQueueCustomer.updateStartTime(this.getTime());
      
      if (!this.bank.queueIsEmpty()) {
        Customer bankQueueCustomer = this.bank.removeCustomer();
        bankQueueCustomer.updateStartTime(this.getTime());
    
        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(counterQueueCustomer, this.bank, this.counter),
          new JoinCounterQueue(bankQueueCustomer, this.counter)
        };
      } else {
        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(counterQueueCustomer, this.bank, this.counter)
        };
      }
    } else { // If there are no counter queues
      if (!this.bank.queueIsEmpty()) {
        Customer bankQueueCustomer = this.bank.removeCustomer();
        bankQueueCustomer.updateStartTime(this.getTime());

        return new Event[] {
          new Departure(this.getTime(), this.customer),
          new ServiceBegin(bankQueueCustomer, this.bank, this.counter)
        };
      } else {
        return new Event[] {
          new Departure(this.getTime(), this.customer)
        };
      }
    }
  }

}
