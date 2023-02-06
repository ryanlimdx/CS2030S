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
    String str = String.valueOf(this.counterId);
    return "(by S" + str + ")";
  }
  
  public boolean getAvailability() {
    return this.available;
  }

  public void setAvailability(boolean availability) {
    this.available = availability;
  }
}
