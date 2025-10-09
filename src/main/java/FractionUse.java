package fraction;

/**
 * Implementation of the Fraction interface.
 * Represents a fraction with integer numerator and positive integer denominator.
 */
public class FractionUse implements Fraction {

  private int numerator;
  private int denominator;

  /**
   * Constructs a new FractionUse with the given numerator and denominator.
   * Denominator must be positive.
   *
   * @param numerator the numerator
   * @param denominator the denominator (must be positive)
   * @throws IllegalArgumentException if denominator is not positive
   */
  public FractionUse(int numerator, int denominator) {
    if (denominator <= 0) {
      throw new IllegalArgumentException("Denominator must be positive.");
    }
    // Normalize negative sign to numerator
    if (denominator < 0) {
      numerator *= -1;
      denominator *= -1;
    }
    this.numerator = numerator;
    this.denominator = denominator;
    simplify();
  }

  @Override
  public int getNumerator() {
    return numerator;
  }

  @Override
  public int getDenominator() {
    return denominator;
  }

  @Override
  public void setNumerator(int numerator) {
    this.numerator = numerator;
    simplify();
  }

  @Override
  public void setDenominator(int denominator) {
    if (denominator <= 0) {
      throw new IllegalArgumentException("Denominator must be positive.");
    }
    this.denominator = denominator;
    simplify();
  }

  @Override
  public double toDouble() {
    return (double) numerator / denominator;
  }

  @Override
  public Fraction reciprocal() {
    if (numerator == 0) {
      throw new ArithmeticException("Cannot take reciprocal of zero.");
    }
    return new FractionUse(denominator * (numerator < 0 ? -1 : 1), Math.abs(numerator));
  }

  @Override
  public Fraction add(Fraction other) {
    int newNumerator = this.numerator * other.getDenominator() +
        other.getNumerator() * this.denominator;
    int newDenominator = this.denominator * other.getDenominator();
    return new FractionUse(newNumerator, newDenominator);
  }

  @Override
  public int compareTo(Fraction other) {
    // Cross-multiply to compare without converting to double
    return this.numerator * other.getDenominator() -
        other.getNumerator() * this.denominator;
  }

  @Override
  public String toString() {
    return numerator + " / " + denominator;
  }

  /**
   * Simplifies the fraction to its lowest terms using Euclid’s algorithm.
   */
  private void simplify() {
    int gcd = gcd(Math.abs(numerator), denominator);
    numerator /= gcd;
    denominator /= gcd;
  }

  /**
   * Computes the greatest common divisor using Euclid's algorithm.
   */
  private static int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }
}
