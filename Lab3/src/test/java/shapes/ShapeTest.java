package shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shapes.Point2D;

public class ShapeTest {

  @Test
  public void testCompareTo() {
    Shape circle = new Circle(new Point2D(0, 0), 1);        // area ≈ 3.14
    Shape rectangle = new Rectangle(new Point2D(0, 0), 2, 2); // area = 4
    Shape triangle = new Triangle(
        new Point2D(0, 0),
        new Point2D(4, 0),
        new Point2D(0, 3)
    );  // area = 6

    assertTrue(circle.compareTo(rectangle) < 0, "Circle should be smaller than Rectangle");
    assertTrue(rectangle.compareTo(triangle) < 0, "Rectangle should be smaller than Triangle");
    assertEquals(0, triangle.compareTo(triangle), "Triangle should be equal to itself");
  }

  @Test
  public void testEquals() {
    Shape circle1 = new Circle(new Point2D(0, 0), 5);
    Shape circle2 = new Circle(new Point2D(0, 0), 5);
    Shape circle3 = new Circle(new Point2D(1, 1), 5);

    assertEquals(circle1, circle2, "Circles with same center and radius should be equal");
    assertNotEquals(circle1, circle3, "Circles with different centers should not be equal");
  }

  @Test
  public void testToString() {
    Shape rect = new Rectangle(new Point2D(1, 2), 4, 5);
    String output = rect.toString();
    assertTrue(output.contains("Rectangle"), "ToString should contain 'Rectangle'");
    assertTrue(output.contains("(1.0, 2.0)"), "ToString should include the reference point");
  }
}
