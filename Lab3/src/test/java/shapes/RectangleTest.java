package shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shapes.Point2D;

public class RectangleTest {

  @Test
  public void testAreaAndPerimeter() {
    Rectangle rect = new Rectangle(new AbstractShape.Point2D(0, 0), 4, 3);
    assertEquals(12.0, rect.getArea(), 0.0001);
    assertEquals(14.0, rect.getPerimeter(), 0.0001);
  }

  @Test
  public void testToString() {
    Rectangle rect = new Rectangle(new AbstractShape.Point2D(1, 1), 2, 5);
    assertTrue(rect.toString().contains("Rectangle"));
  }

}
