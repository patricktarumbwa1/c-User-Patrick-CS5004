package shapes;

import shapes.Point2D;

public abstract class AbstractShape implements Shape {

  protected final Point2D referencePoint;


  public AbstractShape(Point2D referencePoint) {
    if (referencePoint == null) {
      throw new IllegalArgumentException("Reference point cannot be null.");
    }
    this.referencePoint = referencePoint;
  }


  @Override
  public Point2D getReferencePoint() {
    return referencePoint;
  }


  @Override
  public int compareTo(Shape other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot compare to a null shape.");
    }
    return Double.compare(this.getArea(), other.getArea());
  }


  @Override
  public String toString() {
    return String.format("Shape at reference point %s", referencePoint);
  }
}
