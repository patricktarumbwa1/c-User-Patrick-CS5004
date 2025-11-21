import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestView {

  @Test
  public void testShowStringWritesCorrectly() throws IOException {
    StringBuffer out = new StringBuffer();
    IView view = new TextView(out);

    view.showString("abc");
    String result = out.toString();

    assertTrue(result.contains("String: abc"));
    assertTrue(result.endsWith("\n"));
  }

  @Test
  public void testShowOptionsAndPrompts() throws IOException {
    StringBuffer out = new StringBuffer();
    IView view = new TextView(out);

    view.showOptions();
    String s = out.toString();

    assertTrue(s.contains("Menu:"));
    assertTrue(s.contains("Enter your choice"));
  }
}
