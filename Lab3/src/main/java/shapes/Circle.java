package shapes;

public class Circle extends AbstractShape {
  private double radius;

  public Circle(Point2D center, double radius) {
    super(center);
    if (radius <= 0) {
      throw new IllegalArgumentException("Radius must be positive.");
    }
    this.radius = radius;
  }

  @Override
  public double getArea() {
    return Math.PI * radius * radius;
  }

  @Override
  public double getPerimeter() {
    return 2 * Math.PI * radius;
  }

  @Override
  public String toString() {
    return "Circle with center " + referencePoint + " and radius " + radius;
  }
}