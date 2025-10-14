package shapes;


public interface Shape extends Comparable<Shape> {

  double getArea();

  double getPerimeter();

  String toString();

  Point2D getReferencePoint();
}
