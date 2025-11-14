package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

/**
 *  test to show Controller7 bug using Mock
 */
public class TestController7Mock {

  @Test
  public void testController7InputBehavior() throws IOException {
    // Input: simulate user typing "+ 5 3" then "+ 11 12" then quit
    StringReader in = new StringReader("+ 5 3 + 11 12 q");

    //  output collector
    StringBuilder out = new StringBuilder();

    // controller to test
    Controller7 controller = new Controller7(in, out);

    // mock model
    MockCalculatorModel mockModel = new MockCalculatorModel();

    // run
    controller.go(mockModel);

    int[] firstCall = mockModel.receivedInputs.get(0);
    int[] secondCall = mockModel.receivedInputs.get(1);

    assertEquals(5, firstCall[0], "First argument should be 5");
    assertEquals(3, firstCall[1], "Second argument should be 3");

    assertEquals(11, secondCall[0], "First argument should be 11");
    assertEquals(12, secondCall[1], "Second argument should be 12");
  }
}
