/**
 * Represents a Person with a name.
 */
import java.util.Objects;

public class Person {
  private String name;

  /** Constructs a Person object.
   * @param name the person's name */
  public Person(String name) {
    this.name = name;
  }

  /** @return the person's name */
  public String getName() {
    return name;
  }

  /** Sets the person's name */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("Person{name='%s'}", name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Person))
      return false;
    Person other = (Person) obj;
    return Objects.equals(name, other.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
