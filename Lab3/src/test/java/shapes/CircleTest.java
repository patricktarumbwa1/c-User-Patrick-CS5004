package shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shapes.Point2D;

public class CircleTest {

  @Test
  public void testAreaAndPerimeter() {
    Circle circle = new Circle(new Point2D(0, 0), 3);  // Use Point2D directly
    assertEquals(Math.PI * 9, circle.getArea(), 0.0001);
    assertEquals(2 * Math.PI * 3, circle.getPerimeter(), 0.0001);
  }

  @Test
  public void testToString() {
    Circle circle = new Circle(new Point2D(1, 1), 5);
    assertTrue(circle.toString().contains("Circle"));
  }

}
