package shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import shapes.Point2D;

public class TriangleTest {

  @Test
  public void testAreaAndPerimeter() {
    AbstractShape.Point2D p1 = new AbstractShape.Point2D(0, 0);
    AbstractShape.Point2D p2 = new AbstractShape.Point2D(4, 0);
    AbstractShape.Point2D p3 = new AbstractShape.Point2D(0, 3);

    Triangle triangle = new Triangle(p1, p2, p3);

    assertEquals(6.0, triangle.getArea(), 0.0001);
    assertEquals(12.0, triangle.getPerimeter(), 0.0001);
  }

  @Test
  public void testInvalidTriangleSamePoints() {
    AbstractShape.Point2D p1 = new AbstractShape.Point2D(0, 0);
    AbstractShape.Point2D p2 = new AbstractShape.Point2D(0, 0);
    AbstractShape.Point2D p3 = new AbstractShape.Point2D(1, 1);

    assertThrows(IllegalArgumentException.class, () -> {
      new Triangle(p1, p2, p3);
    });
  }

  @Test
  public void testToString() {
    AbstractShape.Point2D p1 = new AbstractShape.Point2D(1, 1);
    AbstractShape.Point2D p2 = new AbstractShape.Point2D(4, 1);
    AbstractShape.Point2D p3 = new AbstractShape.Point2D(1, 5);
    Triangle t = new Triangle(p1, p2, p3);
    assertTrue(t.toString().contains("Triangle with points"));
  }
}
