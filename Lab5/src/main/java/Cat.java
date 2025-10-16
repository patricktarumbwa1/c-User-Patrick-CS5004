/**
 * Represents a Cat with a name, age, color, and owner.
 *
 * <p>This class includes a constructor, getters, setters for age and owner,
 * and standard methods such as toString, equals, and hashCode.
 */

import java.util.Objects;

public class Cat {
  private String name;
  private int age;
  private Color color;
  private Person owner;

  /**
   * Constructs a Cat object.
   *
   * @param name  the name of the cat
   * @param age   the age of the cat in years
   * @param color the color of the cat
   * @param owner the owner of the cat
   */
  public Cat(String name, int age, Color color, Person owner) {
    this.name = name;
    this.age = age;
    this.color = color;
    this.owner = owner;
  }

  /** @return the cat's name */
  public String getName() {
    return name;
  }

  /** @return the cat's age */
  public int getAge() {
    return age;
  }

  /** @return the cat's color */
  public Color getColor() {
    return color;
  }

  /** @return the cat's owner */
  public Person getOwner() {
    return owner;
  }

  /**
   * Sets the age of the cat.
   *
   * @param age the new age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Sets the owner of the cat.
   *
   * @param owner the new owner
   */
  public void setOwner(Person owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return String.format("Cat{name='%s', age=%d, color=%s, owner=%s}",
        name, age, color, owner);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Cat))
      return false;
    Cat other = (Cat) obj;
    return age == other.age &&
        Objects.equals(name, other.name) &&
        color == other.color &&
        Objects.equals(owner, other.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, color, owner);
  }
}
