package fraction;

/**
 * Interface for a Fraction with integer numerator and positive integer denominator.
 */
public interface Fraction extends Comparable<Fraction> {

  /**
   * Gets the numerator of the fraction.
   * @return the numerator
   */
  int getNumerator();

  /**
   * Gets the denominator of the fraction.
   * Always a positive integer.
   * @return the denominator
   */
  int getDenominator();

  /**
   * Sets the numerator of the fraction.
   * @param numerator the new numerator
   */
  void setNumerator(int numerator);

  /**
   * Sets the denominator of the fraction.
   * Denominator must be positive.
   * @param denominator the new denominator
   * @throws IllegalArgumentException if denominator is not positive
   */
  void setDenominator(int denominator);

  /**
   * Converts the fraction to a double value.
   * @return the decimal (scientific) value of the fraction
   */
  double toDouble();

  /**
   * Returns the reciprocal of this fraction.
   * @return a new Fraction representing the reciprocal
   * @throws ArithmeticException if the numerator is zero
   */
  Fraction reciprocal();

  /**
   * Adds this fraction with another.
   * @param other the other fraction
   * @return a new Fraction representing the sum
   */
  Fraction add(Fraction other);

  /**
   * Compares this fraction to another fraction.
   * @param other the other fraction
   * @return negative if this < other, 0 if equal, positive if this > other
   */
  @Override
  int compareTo(Fraction other);

}
