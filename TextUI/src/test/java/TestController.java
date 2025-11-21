import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;

public class TestController {

  @Test
  public void testEnterStringThenQuit() {
    IModel model = new Model();
    StringBuffer out = new StringBuffer();

    // Simulated user input: choose E, then type HelloWorld, then Q
    Readable input = new StringReader("E\nHelloWorld\nQ\n");

    IView view = new TextView(out);
    IController controller = new TextController(model, input, view);

    controller.go();

    // model should contain the entered string
    assertEquals("HelloWorld", model.getString());

    String text = out.toString();
    assertTrue(text.contains("Enter the string to be echoed"));
    assertTrue(text.contains("Menu:"));
  }

  @Test
  public void testInvalidOptionIsHandled() {
    IModel model = new Model();
    StringBuffer out = new StringBuffer();

    Readable input = new StringReader("X\nQ\n"); // X is invalid option, then quit
    IView view = new TextView(out);
    IController controller = new TextController(model, input, view);

    controller.go();

    String result = out.toString();
    assertTrue(result.contains("Invalid option"));
    // model should remain unchanged
    assertEquals("", model.getString());
  }
}
