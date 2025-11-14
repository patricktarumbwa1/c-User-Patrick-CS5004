import java.util.ArrayList;
import java.util.List;

/**
 * Mock Model for testing Controller7 in isolation
 */
public class MockCalculatorModel implements ICalculatorModel {

  // records all inputs sent to add
  public final List<int[]> receivedInputs = new ArrayList<>();

  @Override
  public int add(int a, int b) {
    receivedInputs.add(new int[]{a, b}); // save inputs
    // NOTE: could return something fixed if we wanted, here just sum
    return a + b;
  }
}
