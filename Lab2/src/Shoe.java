

/**
 * Represents a shoe with type, color, brand and size
 */

public class Shoe{
  private final Type type;
  private final Color color;
  private final Brand brand;
  private final double size;

  /**
   * Constructs a Shoe object
   *
   * @param type the Type of shoe
   * @param color the color of the shoe
   * @param brand the brand of the shoe
   * @param size the size of the shoe
   * @throws IllegalArgumentException if NIKE brand and type is BALLET
   */

  public Shoe(Type type, Color color, Brand brand, double size) {
    if (brand == Brand.NIKE && type == Type.BALLET) {
      throw new IllegalArgumentException("NIKE does not sell ballet shoes.");
    }
    this.type = type;
    this.color = color;
    this.brand = brand;
    this.size = size;
  }

  /**
   * Gets the type of shoe.
   * @return the Type
   */
  public Type getType() {
    return type;
  }

  /**
   * Gets the color of the shoe
   * @return the color
   */
  public Color getColor(){
    return color;
  }

  /**
   * Gets the brand of the shoe
   * @return the brand
   */
  public Brand getBrand(){
    return brand;
  }

  /**
   * Gets the size of the shoe
   * @return the size
   */
  public double getSize(){
    return size;
  }

  /**
   * Returns a string summary of the shoe.
   * @return formatted shoe details
   */
  @Override
  public String toString() {
    String colorStr;
    switch (color) {
      case BROWN:
        colorStr = "Brown";
        break;
      case BLACK:
        colorStr = "Black";
        break;
      case WHITE:
        colorStr = "White";
        break;
      case RED:
        colorStr = "Red";
        break;
      default:
        colorStr = "Multicolor";
    }

    return String.format("Shoe [Type: %s, Color: %s, Brand: %s, Size: %.1f]",
        type.toString().toLowerCase(),
        colorStr,
        brand.toString().toUpperCase(),
        size);
  }
}