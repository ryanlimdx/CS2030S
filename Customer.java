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

  // The time as a customer starts an activity.
  private double startTime;
  
  // The intended task a customer wants to do.
  private Task task;

  public Customer(int customerId, double arrivalTime, 
      double serviceTime, int intendedAction) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.startTime = arrivalTime;
    this.task = Task.get(intendedAction);
  }
  
  @Override
  public String toString() {
    String str = String.valueOf(this.customerId);
    return "C" + str;
  }
  
  public Counter goToCounter(Bank bank) {
    return bank.availableCounter();
  }

  // Calculates the time when the service ends.
  public double serviceEndTime() {
    return startTime + this.serviceTime;
  }

  // Get customer's arrival time.
  public double getArrivalTime() {
    return this.arrivalTime;
  }

  // Updates the start time.
  public void updateStartTime(double time) {
    startTime = time;
  }

  // Gets the start time.
  public double getStartTime() {
    return startTime;
  }
  
  // Get customer's action.
  public Task getTask() {
    return this.task;
  }
}
