import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestModel {

  @Test
  public void testDefaultAndSetGet() {
    IModel model = new Model();

    // default state should be empty string
    assertEquals("", model.getString());

    // set and get
    model.setString("hello");
    assertEquals("hello", model.getString());
  }

  @Test
  public void testOverwriteBehavior() {
    IModel model = new Model();
    model.setString("first");
    model.setString("second");

    assertEquals("second", model.getString());
    assertNotEquals("first", model.getString());
  }
}
