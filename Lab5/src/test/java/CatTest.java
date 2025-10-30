import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CatTest {

  @Test
  public void testSetters() {
    Person owner = new Person("Alice");
    Cat cat = new Cat("Milo", 2, Color.GRAY, owner);

    cat.setAge(3);
    cat.setOwner(new Person("Bob"));

    assertEquals(3, cat.getAge());
    assertEquals("Bob", cat.getOwner().getName());
  }

  @Test
  public void testEqualsAndHashCode() {
    Person owner = new Person("Alice");

    Cat cat1 = new Cat("Luna", 2, Color.BLACK, owner);
    Cat cat2 = new Cat("Luna", 2, Color.BLACK, new Person("Alice"));
    Cat cat3 = new Cat("Milo", 2, Color.BLACK, owner);

    // Cats 1 and 2 should be equal
    assertEquals(cat1, cat2);
    assertEquals(cat1.hashCode(), cat2.hashCode());

    // Cats 1 and 3 should not be equal
    assertNotEquals(cat1, cat3);
    assertNotEquals(cat1.hashCode(), cat3.hashCode());
  }

  @Test
  public void testToString() {
    Person owner = new Person("Alice");
    Cat cat = new Cat("Luna", 2, Color.BLACK, owner);
    String result = cat.toString();
    assertTrue(result.contains("Luna"));
    assertTrue(result.contains("BLACK"));
  }
}
