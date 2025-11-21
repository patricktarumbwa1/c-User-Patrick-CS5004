import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;

public class TestIntegration {

  @Test
  public void testFullProgramFlow() {
    IModel model = new Model();
    StringBuffer out = new StringBuffer();
    Readable input = new StringReader("E\nTestString\nQ\n");

    IView view = new TextView(out);
    IController controller = new TextController(model, input, view);

    controller.go();

    assertEquals("TestString", model.getString());

    String output = out.toString();
    assertTrue(output.contains("Enter your choice"));
    assertTrue(output.contains("Enter the string to be echoed"));
    assertTrue(output.contains("String:"));
  }
}
