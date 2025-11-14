package calculator;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main app for Controller7
 */
public class SimpleCalc7 {
  public static void main(String[] args) {
    try {
      Controller7 controller = new Controller7(
          new InputStreamReader(System.in),
          System.out
      );

      controller.go(new Calculator()); // real calculator
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
