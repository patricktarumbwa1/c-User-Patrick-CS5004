package shapes;

public class Triangle extends AbstractShape {
  private final Point2D point2;
  private final Point2D point3;

  public Triangle(Point2D p1, Point2D p2, Point2D p3) {
    super(p1); //

    if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
      throw new IllegalArgumentException("Triangle points must be distinct.");
    }

    this.point2 = p2;
    this.point3 = p3;
  }

  @Override
  public double getPerimeter() {
    double a = referencePoint.distance(point2);
    double b = point2.distance(point3);
    double c = point3.distance(referencePoint);
    return a + b + c;
  }

  @Override
  public double getArea() {
    double a = referencePoint.distance(point2);
    double b = point2.distance(point3);
    double c = point3.distance(referencePoint);
    double s = (a + b + c) / 2.0;
    return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Heron's formula
  }

  @Override
  public String toString() {
    return "Triangle with points " +
        referencePoint + ", " +
        point2 + ", " +
        point3;
  }
}
