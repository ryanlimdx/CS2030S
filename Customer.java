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
  
  // The time the customer starts service.
  private double serviceStartTime;

  // The time the customer takes to be serviced.
  private double serviceTime;
  
  // The intended task a customer wants to do.
  private int intendedAction;

  // Intended Actions of the Customers
  private final int deposit = 0;
  private final int withdrawal = 1;

  public Customer(int customerId, double arrivalTime, 
      double serviceTime, int intendedAction) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.intendedAction = intendedAction;
    this.serviceStartTime = arrivalTime; // initialise default value before updating if needed.
  }
  
  @Override
  public String toString() {
    String str = String.valueOf(this.customerId);
    return "C" + str;
  }
  
  public void updateServiceStartTime(double currentTime) {
    this.serviceStartTime = currentTime;
  }

  // Calculates the time when the service ends.
  public double serviceEndTime() {
    return this.serviceStartTime + this.serviceTime;
  }

  // Get customer's arrival time.
  public double getArrivalTime() {
    return this.arrivalTime;
  }
  
  // Get customer's (updated) service start time.
  public double getServiceStartTime() {
    return this.serviceStartTime;
  }

  // Get customer's intended action (can be expanded if more actions are needed; openAccount etc)
  public String getAction() {
    return (this.intendedAction == deposit) ? "Deposit" : "Withdrawal";
  }
}
