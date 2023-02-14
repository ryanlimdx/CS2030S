/**
 * This class implements a task action to return
 * a customer's task.
 *
 * @author Ryan Lim Ding Xuan (Group 10J)
 */

abstract class Task {

  // Available bank tasks.
  private static final int deposit = 0;
  private static final int withdrawal = 1;
  private static final int openAccount = 2;
  
  public Task() {
  }

  public static Task get(int intendedAction) {
    if (intendedAction == deposit) {
      Deposit depositAction = new Deposit();
      return depositAction;
    } else if (intendedAction == withdrawal) {
      Withdrawal withdrawalAction = new Withdrawal();
      return withdrawalAction;
    } else {
      OpenAccount openAccountAction = new OpenAccount();
      return openAccountAction;
    }
  }
}
