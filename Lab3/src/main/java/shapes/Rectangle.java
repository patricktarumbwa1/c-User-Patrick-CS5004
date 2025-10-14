package shapes;

public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  public Rectangle(Point2D topLeft, double width, double height) {
    super(topLeft);
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
    this.width = width;
    this.height = height;
  }

  public double getArea() {
    return width * height;
  }

  public double getPerimeter() {
    return 2 * (width + height);
  }

  public String toString() {
    return "Rectangle at " + referencePoint + " with width " + width + " and height " + height;
  }
}
