import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoeTest {

  @Test
  public void testConstructorAndGetters() {
    Shoe shoe = new Shoe(Type.LOAFER, Color.BROWN, Brand.ADIDAS, 9.5);

    assertEquals(Type.LOAFER, shoe.getType());
    assertEquals(Color.BROWN, shoe.getColor());
    assertEquals(Brand.ADIDAS, shoe.getBrand());
    assertEquals(9.5, shoe.getSize(), 0.01);
  }

  @Test
  public void testToStringWithKnownValues() {
    Shoe shoe = new Shoe(Type.SNEAKER, Color.BLACK, Brand.PUMA, 10.0);
    String output = shoe.toString();

    assertTrue(output.contains("Type: sneaker"));
    assertTrue(output.contains("Color: Black"));
    assertTrue(output.contains("Brand: PUMA"));
    assertTrue(output.contains("Size: 10.0"));
  }

  @Test
  public void testToStringWithDefaultColorCase() {
    Shoe shoe = new Shoe(Type.HIKING, Color.MULTICOLOR, Brand.CROCS, 11.5);
    String output = shoe.toString();

    assertTrue(output.contains("Color: Multicolor"));
    assertTrue(output.startsWith("Shoe [Type: hiking"));
  }

  @Test
  public void testIllegalNikeBalletCombination() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Shoe(Type.BALLET, Color.RED, Brand.NIKE, 8.0);
    });

    assertEquals("NIKE does not sell ballet shoes.", exception.getMessage());
  }
}
