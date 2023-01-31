/**
 * This class implements a Customer and includes details about the customer.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class Customer {
  // The id of the customer.
  private final int customerId;
 
  // The time the customer arrives at the bank.
  private double arrivalTime;
 
  // The time the customer takes to be serviced.
  private double serviceTime;

  public Customer(int customerId, double arrivalTime, double serviceTime) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  @Override
  public String toString() {
    String str = String.valueOf(customerId);
    return ": Customer " + str;
  }
  
  // Calculates the time when the service ends.
  public double endServiceTime(double currentTime) {
    return currentTime + serviceTime;
  }
}
