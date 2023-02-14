/**
 *  This interface implements typical queue actions required in a queue.
 *
 *  @author Ryan Lim Ding Xuan (10J)
 */

public interface QueueActions {
  
  public boolean queueIsFull();

  public boolean queueIsEmpty();

  public boolean addCustomer(Customer customer);

  public Customer removeCustomer();
}

