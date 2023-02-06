import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */

class BankSimulation extends Simulation {
  // The bank to be simulated. 
  private Bank bank;

  // The queue at the bank.
  private Queue queue;

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;
  
  /** 
   * Constructor for a bank simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()]; // number of customers.
    int numOfCounters = sc.nextInt();
    int maxQueueSize = sc.nextInt();
    this.queue = new Queue(maxQueueSize);
    this.bank = new Bank(numOfCounters, this.queue);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int intendedAction = sc.nextInt();
      Customer customer = new Customer(id, arrivalTime, serviceTime, intendedAction);
      initEvents[id] = new Arrival(customer, this.bank);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
