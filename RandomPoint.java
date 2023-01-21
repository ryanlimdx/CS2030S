import java.util.Random;

// TODO
public class RandomPoint extends Point {

  private static long seed = 1;
  private static Random randomValue = new Random(seed);

  public RandomPoint(double minX, double maxX, double minY, double maxY) {
    super(0.0, 0.0);
    
    double doubleX = randomValue.nextDouble() * (maxX - minX) + minX;
    double doubleY = randomValue.nextDouble() * (maxY - minY) + minY;
    setX(doubleX);
    setY(doubleY);
  }

  public static void setSeed(long seed) {
    RandomPoint.seed = seed;
    
    // creates a new random value after this method is called again
    randomValue = new Random(seed);
  }
}
