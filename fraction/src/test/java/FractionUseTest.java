package fraction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FractionUseTest {

  @Test
  void testConstructorAndToString() {
    Fraction f = new FractionUse(4, 8);
    assertEquals("1 / 2", f.toString());
  }

  @Test
  void testNegativeFraction() {
    Fraction f = new fraction.FractionUse(-5, 9);
    assertEquals("−5 / 3", f.toString().replace("-", "−")); // For visual check
  }

  @Test
  void testZeroNumerator() {
    Fraction f = new FractionUse(0, 5);
    assertEquals("0 / 1", f.toString());
  }

  @Test
  void testSetNumerator() {
    Fraction f = new FractionUse(1, 2);
    f.setNumerator(3);
    assertEquals("3 / 2", f.toString());
  }

  @Test
  void testSetDenominatorValid() {
    Fraction f = new FractionUse(3, 1);
    f.setDenominator(4);
    assertEquals("1 / 4", f.toString());
  }

  @Test
  void testSetDenominatorInvalid() {
    Fraction f = new FractionUse(2, 1);
    assertThrows(IllegalArgumentException.class, () -> f.setDenominator(0));
  }

  @Test
  void testToDouble() {
    Fraction f = new FractionUse(1, 2);
    assertEquals(0.5, f.toDouble(), 0.0001);
  }

  @Test
  void testReciprocal() {
    Fraction f = new FractionUse(2, 3);
    Fraction r = f.reciprocal();
    assertEquals("3 / 2", r.toString());
  }

  @Test
  void testReciprocalOfZero() {
    Fraction f = new FractionUse(5, 1);
    assertThrows(ArithmeticException.class, f::reciprocal);
  }

  @Test
  void testAddition() {
    Fraction f1 = new FractionUse(1, 2);
    Fraction f2 = new FractionUse(1, 3);
    Fraction result = f1.add(f2);
    assertEquals("5 / 6", result.toString());
  }

  @Test
  void testCompareTo() {
    Fraction f1 = new FractionUse(1, 2);
    Fraction f2 = new FractionUse(2, 3);
    assertTrue(f1.compareTo(f2) < 0);
    assertTrue(f2.compareTo(f1) > 0);
    assertEquals(0, f1.compareTo(new FractionUse(2, 4)));
  }
}

