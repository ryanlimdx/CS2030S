/** 
 * This class implements a Counter at the bank.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */ 

class Counter {
  // The id of the counter.
  private final int counterId;

  // Indicates availability of the counter.
  private boolean available;

  public Counter(int counterId, boolean available) {
    this.counterId = counterId;
    this.available = available;
  }
  
  @Override
  public String toString() {
    String str = String.valueOf(counterId);
    return "(by Counter " + str + ")";
  }
  
  public boolean getAvailability() {
    return available;
  }

  public void setAvailability(boolean availability) {
    available = availability;
  }
}
