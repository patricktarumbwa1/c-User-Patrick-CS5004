package calculator;

import java.io.IOException;
import java.io.Readable;
import java.io.Appendable;
import java.util.Objects;
import java.util.Scanner;

/**
 * Controller7 for calculator
 */
public class Controller7 {

  final Readable in;
  final Appendable out;

  Controller7(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  public void go(ICalculatorModel calc) throws IOException {
    Objects.requireNonNull(calc);

    int num1, num2;
    Scanner scan = new Scanner(this.in);

    while (true) {
      switch (scan.next()) {
        case "+":
          num1 = scan.nextInt() + 1;
          num2 = scan.nextInt() - 1;

          // output result
          this.out.append(String.format("%d\n", calc.add(num1, num2)));
          break;

        case "q":
          return;

        default:
          throw new IllegalStateException("Unexpected value: " + scan.next());
      }
    }
  }
}
