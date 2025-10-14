package shapes;

public class Point2D {

  private final double x;
  private final double y;


  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }


  public double distance(Point2D other) {
    double dx = this.x - other.x;
    double dy = this.y - other.y;
    return Math.sqrt(dx * dx + dy * dy);
  }

  @Override
  public String toString() {
    return String.format("Point2D(x=%.2f, y=%.2f)", x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Point2D)) return false;
    Point2D other = (Point2D) obj;
    return Double.compare(x, other.x) == 0 &&
        Double.compare(y, other.y) == 0;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(x) * 31 + Double.hashCode(y);
  }
}
